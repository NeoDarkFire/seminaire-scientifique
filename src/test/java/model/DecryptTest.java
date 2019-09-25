package model;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

class DecryptTest {

    @Test
    void it_should_not_allow_initial_keys_bigger_than_size() {
        try {
            Decrypt.decrypt("test 123".getBytes(), 2, "big", (p) -> {});
            fail();
        } catch (final InvalidParameterException e) {
            return;
        }
        fail();
    }

    @Test
    void it_should_not_allow_initial_keys_with_non_lowercase_alphabet() {
        try {
            Decrypt.decrypt("test 123".getBytes(), 10, "INVALID", (p) -> {});
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

        // The last byte can be whatever while it's a valid one
        assertEquals((int) Math.pow(26, 3), count.intValue());
    }

}
