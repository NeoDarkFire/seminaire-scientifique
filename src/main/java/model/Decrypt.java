package model;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

final class Decrypt {

    private Decrypt() {}

    static Set<byte[]> getPotentialKeys(final int start, final byte[] keyBytes, final Function<byte[], Boolean> fn) {
        final int size = keyBytes.length;
        assert start < size : "index starts out of bounds";
        final Set<byte[]> set = new HashSet<>();

        computeBytesForIndex(start, keyBytes, set, fn);

        return set;
    }

    private static void computeBytesForIndex(final int index,
                                             final byte[] keyBytes,
                                             final Set<byte[]> set,
                                             final Function<byte[], Boolean> fn) {
        final int size = keyBytes.length;
        for (byte b = 97; b < 97+26; b++) {
            final byte[] array = keyBytes.clone();
            array[index] = b;
            if (index < size-2) {
                computeBytesForIndex(index+1, array, set, fn);
            } else if (index == size - 2) {
                if (fn.apply(array)) {
                    set.add(array);
                }
            }
        }
    }


    static Optional<byte[]> decrypt(final byte[] inputBytes,
                                           final int keySize,
                                           final Consumer<Double> progressUpdate) {
        return decrypt(inputBytes, keySize, "", progressUpdate);
    }

    /// Brute force keys -- A key is only lowercase alphabet
    static Optional<byte[]> decrypt(final byte[] inputBytes,
                                           final int keySize,
                                           final String initialKey,
                                           final Consumer<Double> progressUpdate) {
        final byte[] initialKeyBytes = initialKey.getBytes();
        final XorEncryption encryption = new XorEncryption();
        final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        byte[] keyBytes = new byte[keySize];

        // Check sizes
        if (initialKeyBytes.length > keySize) {
            throw new InvalidParameterException("initialKey is bigger than keySize");
        }
        // Check and copy initial key
        for (int i = 0; i < initialKeyBytes.length; i++) {
            final byte b = initialKeyBytes[i];
            if (b >= 97 && b < 97+26) {
                // Copy in the key buffer
                keyBytes[i] = b;
            } else {
                throw new InvalidParameterException("initialKey does not contain only lowercase alphabet");
            }
        }
        // Set last byte to 'a' ASCII, because any lowercase letter in last position gives the same output
        keyBytes[keySize-1] = 97;

        System.out.printf("Combinations: %d\n", (long) Math.pow(26, keySize - initialKeyBytes.length));

        // First pass
        final Set<byte[]> potentialKeys = getPotentialKeys(initialKeyBytes.length, keyBytes, (key) -> {
            encryption.setKey(key);
            final byte[] outputBytes = encryption.decrypt(inputBytes);
            // Check if it looks like a word (less than 35 letters before the first "separation" char
            for (int i = 1; i < 35; i++) {
                if (i == outputBytes.length) {
                    return true;  // Too small to evict
                }
                final byte b = outputBytes[i];
                if ((b >= 0x09 && b <= 0x0D) || b == 0x20 || b == 0x25 || b == 0x2D || b == 0x2E || b == 0x2F) {
                    // Try to decode as UTF-8
                    try {
                        decoder.decode(ByteBuffer.wrap(outputBytes));
                        return true;
                    } catch (final CharacterCodingException e) {
                        return false;
                    }
                }
            }
            return false;
        });

        progressUpdate.accept(20.0);
        System.out.printf("Potential keys: %d\n", potentialKeys.size());

        // Second pass: Check length of every word
        final Set<byte[]> filteredKeys = potentialKeys.stream()
                .filter((key) -> {
                    encryption.setKey(key);
                    final byte[] outputBytes = encryption.decrypt(inputBytes);
                    int count = 0;
                    for (final byte b : outputBytes) {
                        if ((b >= 0x09 && b <= 0x0D) || b == 0x20 || b == 0x25 || b == 0x2D || b == 0x2E || b == 0x2F) {
                            if (count >= 35) {
                                return false;
                            } else {
                                count = 0;
                            }
                        } else {
                            count++;
                        }
                    }
                    // Every word is not too long
                    return count < outputBytes.length;
                }).collect(Collectors.toSet());

        progressUpdate.accept(40.0);
        System.out.printf("Filtered keys: %d\n", filteredKeys.size());

        // Connect to the database to check dictionary
        AtomicReference<Double> progress = new AtomicReference<>(0.4);
        final double increment = (1.0 - progress.get()) / (double) filteredKeys.size();
        final Optional<byte[]> decryptedKey = filteredKeys.stream()
                .map((key) -> {
                    encryption.setKey(key);
                    final byte[] outputBytes = encryption.decrypt(inputBytes);

                    final String[] words = new String(outputBytes).trim().split("[\\s\\p{Punct}]+");

                    int good_count = 0;
                    int bad_count = 0;
                    for (final String word : words) {
                        if (Map_Dict.hasWord(word.toLowerCase())) {
                            good_count++;
                        } else {
                            bad_count++;
                        }
                        if (good_count + bad_count >= 6) {
                            break;  // Enough, stop early
                        }
                    }
                    progress.updateAndGet(v -> v + increment);
                    progressUpdate.accept(progress.get());
                    return new Tuple(good_count - bad_count, key);
                }).max(Comparator.comparingInt(t -> t.rank))
                .map(tuple -> tuple.key);

        System.out.printf("Database checked key: %s\n", decryptedKey.map(String::new).orElse(""));

        return decryptedKey.map((key) -> {
            encryption.setKey(key);
            return encryption.decrypt(inputBytes);
        });
    }

    private static class Tuple {
        final int rank;
        final byte[] key;
        Tuple(final int rank, final byte[] key) {
            this.rank = rank;
            this.key = key;
        }
    }

}
