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


    public static byte[] decrypt(final byte[] inputBytes, final int keySize) {
        return decrypt(inputBytes, keySize, "");
    }

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


        // Brute force keys -- A key is only lowercase alphabet
        final Set<byte[]> potentialKeys = getPotentialKeys(initialKeyBytes.length, keyBytes, (key) -> {
            encryption.setKey(key);
            final byte[] outputBytes = encryption.decrypt(inputBytes);
            if (Arrays.equals(key, "as".getBytes())) {
                System.out.println(new String(outputBytes));
            }
            try {
                decoder.decode(ByteBuffer.wrap(outputBytes));
                System.out.printf("Valid UTF-8 (key = %s): %s\n",new String(key), new String(outputBytes));
                return true;
            } catch (final CharacterCodingException e) {
//                System.out.println("!!!!!!!! Invalid UTF-8");
                return false;
            }
        });

        System.out.printf("Potential keys: %d\n", potentialKeys.size());

        // TODO: stub
        return null;
    }

}
