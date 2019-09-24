package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class XorEncryptionTest {

    @Test
    void it_should_encrypt_and_decrypt_unicode() {
        final String input = "お前はもう死んでいる";
        final String key = "Muffin time!";
        final IEncryption encryption = new XorEncryption(key.getBytes());

        final byte[] encrypted = encryption.encrypt(input.getBytes());
        final String decrypted = new String(encryption.decrypt(encrypted));

        assertEquals(input, decrypted);
    }

    @Test
    void it_should_do_nothing_with_an_empty_key() {
        final String input = "お前はもう死んでいる";
        final String key = "";
        final IEncryption encryption = new XorEncryption(key.getBytes());

        final byte[] encrypted = encryption.encrypt(input.getBytes());

        assertEquals(input, new String(encrypted));
    }
}
