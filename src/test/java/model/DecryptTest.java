package model;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

class DecryptTest {

    @Test
    void it_should_not_allow_initial_keys_bigger_than_size() {
        try {
            Decrypt.decrypt("test 123".getBytes(), 2, "big");
            fail();
        } catch (final InvalidParameterException e) {
            return;
        }
        fail();
    }

    @Test
    void it_should_not_allow_initial_keys_with_non_lowercase_alphabet() {
        try {
            Decrypt.decrypt("test 123".getBytes(), 10, "INVALID");
            fail();
        } catch (final InvalidParameterException e) {
            return;
        }
        fail();
    }

    @Test
    void it_should_check_every_possible_keys() {
        AtomicLong count = new AtomicLong();

        Decrypt.getPotentialKeys(0, "asdf".getBytes(), (key) -> {
            count.getAndIncrement();
            return false;
        });

        assertEquals((int) Math.pow(26, 4), count.intValue());
    }

    @Test
    void it_should_decrypt() {
        final String initial = "お前はもう死んでいる";
        final String key = "as";
        final IEncryption encryption = new XorEncryption(key.getBytes());
        final byte[] encoded = encryption.encrypt(initial.getBytes());

        final byte[] decoded = Decrypt.decrypt(encoded, 2);

        assertEquals(initial.getBytes(), decoded);
    }
}
