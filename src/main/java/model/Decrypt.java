package model;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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


    public static byte[] decrypt(final byte[] inputBytes, final int keySize) {
        return decrypt(inputBytes, keySize, "");
    }

    /// Brute force keys -- A key is only lowercase alphabet
    public static byte[] decrypt(final byte[] inputBytes, final int keySize, final String initialKey) {
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

        // First pass
        final Set<byte[]> potentialKeys = getPotentialKeys(initialKeyBytes.length, keyBytes, (key) -> {
            encryption.setKey(key);
            final byte[] outputBytes = encryption.decrypt(inputBytes);
            // Check if it looks like a word (less than 35 letters before the first "separation" char
            for (int i = 1; i < 45; i++) {
                final byte b = outputBytes[i];
                if (b <= 0x20 || b == 0x25 || b == 0x2D || b == 0x2E || b == 0x2F) {
                    // Try to decode as UTF-8
                    try {
                        decoder.decode(ByteBuffer.wrap(outputBytes));
//                        System.out.printf("Potential (key = %s): %s\n",new String(key), new String(outputBytes));
                        return true;
                    } catch (final CharacterCodingException e) {
                        return false;
                    }
                }
            }
            return false;
        });

        System.out.printf("Potential keys: %d\n", potentialKeys.size());

        // Second pass: Check length of every word
        final Set<byte[]> filteredKeys = potentialKeys.stream()
                .filter((key) -> {
                    encryption.setKey(key);
                    final byte[] outputBytes = encryption.decrypt(inputBytes);
                    int count = 0;
                    for (int i = 0; i < outputBytes.length; i++) {
                        final byte b = outputBytes[i];
                        if (b <= 0x20 || b == 0x25 || b == 0x2D || b == 0x2E || b == 0x2F) {
                            if (count >= 45) {
                                return false;
                            } else {
                                count = 0;
                            }
                        } else {
                            count++;
                        }
                    }
                    // Every word is long enough
                    return true;
                }).collect(Collectors.toSet());

        System.out.printf("Filtered keys: %d\n", filteredKeys.size());

        // TODO: stub
        return null;
    }

}
