package model;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
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
            if (index < size-1) {
                computeBytesForIndex(index+1, array, set, fn);
            } else if (index == size - 1) {
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
        if (inputBytes.length == 0) {
            System.out.println("Empty input");
            progressUpdate.accept(1.0);
            return Optional.empty();
        } else if (inputBytes.length == keySize){
            progressUpdate.accept(0.0);
        }
        final byte[] initialKeyBytes = initialKey.getBytes();
        final XorEncryption encryption = new XorEncryption();
        final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
        byte[] keyBytes = new byte[keySize];

        // Check sizes
        if (initialKeyBytes.length > keySize) {
            throw new InvalidParameterException("initialKey is bigger than keySize");
        } else if (initialKeyBytes.length == keySize){
            System.out.println("Already full key");
            progressUpdate.accept(1.0);
            return Optional.of(initialKey.getBytes());
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

        final long combinations = (long) Math.pow(26, keySize - initialKeyBytes.length);
        System.out.printf("Combinations: %d\n", combinations);

        // First pass
        final AtomicLong firstPassCount = new AtomicLong(0);
        final AtomicReference<Double> firstPassProgress = new AtomicReference<>(0.0);
        final long modulo = 1000;
        final double firstPassIncrement = ((double) modulo * 0.3) / (double) combinations;
        final Set<byte[]> potentialKeys = getPotentialKeys(initialKeyBytes.length, keyBytes, (key) -> true);/*{
            if (firstPassCount.get() % modulo == 0) {
                firstPassProgress.updateAndGet(v -> v + firstPassIncrement);
                progressUpdate.accept(firstPassProgress.get());
                Thread.yield();
            }
            encryption.setKey(key);
            final byte[] outputBytes = encryption.decrypt(inputBytes);
            // Try to decode as UTF-8
            try {
                decoder.decode(ByteBuffer.wrap(outputBytes));
                firstPassCount.getAndIncrement();
                return true;
            } catch (final CharacterCodingException e) {
                e.printStackTrace();
                firstPassCount.getAndIncrement();
                return false;
            }
        });*/

        progressUpdate.accept(0.0);
        System.out.printf("Potential keys: %d\n", potentialKeys.size());

        // Second pass: Check length of every word
        final Set<byte[]> filteredKeys = potentialKeys;/*potentialKeys.stream()
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
*/
        progressUpdate.accept(0.0);
        System.out.printf("Filtered keys: %d\n", filteredKeys.size());

        final AtomicReference<Double> progress = new AtomicReference<>(0.0);
        final double increment = (1.0 - progress.get()) / (double) filteredKeys.size();

        // Connect to the database to check dictionary
        try {
            final Set<String> dico = Map_Dict.allWords().stream()
                    .map(word -> Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{M}", ""))
                    .collect(Collectors.toSet());

            final Set<Ranking> rankedSet = filteredKeys.stream()
                    .map((key) -> {
                        encryption.setKey(key);
                        final byte[] outputBytes = encryption.decrypt(inputBytes);

                        final String[] words = new String(outputBytes).trim().split("[\\s.,?!;:/!\\\\|{}+=#&_@]+");

                        int good_count = 0;
                        int bad_count = 0;
                        for (final String word : words) {
                            if (dico.contains(word.toLowerCase())) {
                                good_count++;
                            } else {
                                bad_count++;
                            }
                        }
                        progress.updateAndGet(v -> v + increment);
                        progressUpdate.accept(progress.get());
                        return new Ranking(good_count, bad_count, key);
                    }).collect(Collectors.toSet());

            final Ranking lowest_bad = rankedSet.stream().max(Comparator.comparingLong(r -> r.bad)).get();
            final Ranking highest_good = rankedSet.stream().max(Comparator.comparingLong(r -> r.good)).get();

            final Set<Ranking> filteredSet = rankedSet.stream()
                    .filter(r -> r.bad * lowest_bad.total() <= lowest_bad.bad * r.total())
                    .filter(r -> r.good * highest_good.total() >= highest_good.good * r.total())
                    .collect(Collectors.toSet());

            final AtomicReference<Ranking> goodOne = new AtomicReference<>(null);
            filteredSet.forEach(r -> {
                final boolean isTheOne = Arrays.equals(r.key, "awqpmndfgtej".getBytes());
                if (isTheOne) {
                    goodOne.set(r);
                }
                System.out.printf("[%s]        good: %d        bad: %d        total: %d        %s\n",
                        new String(r.key), r.good, r.bad, r.good + r.bad,
                        isTheOne ? "<-- the good one" : "");
            });
            System.out.println(filteredSet.size());
            {
                Ranking r = goodOne.get();
                if (r == null) {
                    r = rankedSet.stream()
                            .filter(a -> Arrays.equals(a.key, "awqpmndfgtej".getBytes()))
                            .findFirst()
                            .get();
                }
                System.out.printf("[%s]        good: %d        bad: %d        total: %d        <-- inside: %s\n",
                        new String(r.key), r.good, r.bad, r.good + r.bad,
                        goodOne.get() != null ? "true" : "false");
            }

            final Optional<byte[]> decryptedKey = filteredSet.stream()
                    .max(Comparator.comparingDouble((Ranking r) -> (double) r.good / (double) r.total()))
                    .map(r -> r.key);

            System.out.printf("Database checked key: %s\n", decryptedKey.map(String::new).orElse(""));

            return decryptedKey;
        } catch (final Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private static class Ranking {
        final long good;
        final long bad;
        final byte[] key;
        Ranking(final long good, final long bad, final byte[] key) {
            this.good = good;
            this.bad = bad;
            this.key = key;
        }
        long total() {
            return good + bad;
        }
    }

}
