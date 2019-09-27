package model;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
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

    static Set<Ranking> getRankedKeys(final int start, final byte[] keyBytes, final Function<byte[], Ranking> fn) {
        final int size = keyBytes.length;
        assert start < size : "index starts out of bounds";
        final Set<Ranking> set = new HashSet<>();

        computeBytesForIndex(start, keyBytes, set, fn);

        return set;
    }

    private static void computeBytesForIndex(final int index,
                                             final byte[] keyBytes,
                                             final Set<Ranking> set,
                                             final Function<byte[], Ranking> fn) {
        final int size = keyBytes.length;
        for (byte b = 97; b < 97+26; b++) {
            final byte[] array = keyBytes.clone();
            array[index] = b;
            if (index < size-1) {
                computeBytesForIndex(index+1, array, set, fn);
            } else if (index == size - 1) {
                final Ranking r = fn.apply(array);
                if (r != null) {
                    set.add(r);
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
        } else {
            progressUpdate.accept(0.0);
        }
        final byte[] initialKeyBytes = initialKey.getBytes();
        final XorEncryption encryption = new XorEncryption();
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

        // Connect to the database to check dictionary
        final Set<String> dico;
        try {
            dico = Map_Dict.allWords().stream()
                    .map(word -> Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{M}", ""))
                    .collect(Collectors.toSet());
        } catch (final Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }

        final AtomicReference<Double> progress = new AtomicReference<>(0.05);
        progressUpdate.accept(progress.get());
        final long updateStep = 5000;
        final double increment = (1.0 - progress.get()) / ((double) combinations / (double) updateStep);
        final AtomicLong count = new AtomicLong(0);

        final Set<Ranking> rankedSet = getRankedKeys(initialKeyBytes.length, keyBytes, (key) -> {
            // Send update every `updateStep`
            if (count.getAndIncrement() % updateStep == 0) {
                progressUpdate.accept(progress.updateAndGet(v -> Math.min(1.0, v + increment)));
            }

            // Try decryption
            encryption.setKey(key);
            final byte[] outputBytes = encryption.decrypt(inputBytes);

            final String[] words = new String(outputBytes).trim().split("[\\s.,?!;:/!\\\\|(){}+=#&_@]+");

            // Count words
            int good_count = 0;
            int bad_count = 0;
            for (final String word : words) {
                if (dico.contains(word.toLowerCase())) {
                    good_count++;
                } else {
                    bad_count++;
                }
            }
            return new Ranking(good_count, bad_count, key);
        });
        progressUpdate.accept(1.00);

        final Ranking lowest_bad = rankedSet.stream().max(Comparator.comparingLong(r -> r.bad)).get();
        final Ranking highest_good = rankedSet.stream().max(Comparator.comparingLong(r -> r.good)).get();

        final Set<Ranking> filteredSet = rankedSet.stream()
                .filter(r -> r.bad * lowest_bad.total() <= lowest_bad.bad * r.total())
                .filter(r -> r.good * highest_good.total() >= highest_good.good * r.total())
                .collect(Collectors.toSet());

        final Optional<byte[]> decryptedKey = filteredSet.stream()
                .max(Comparator.comparingDouble((Ranking r) -> (double) r.good / (double) r.total()))
                .map(r -> r.key);

        System.out.printf("key: %s\n", decryptedKey.map(String::new).orElse(""));

        return decryptedKey;
    }

    static class Ranking {
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
