package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class DecryptTest {

    @Test
    void it_should_encrypt_and_decrypt_unicode() {
        final String input = "お前はもう死んでいる";
        final String key = "Muffin time!";
        final IEncryption encryption = new XorEncryption(key.getBytes());

        final byte[] encrypted = encryption.encrypt(input.getBytes());
        final String decrypted = new String(encryption.decrypt(encrypted));

        assertEquals(input, decrypted);
    }
}
