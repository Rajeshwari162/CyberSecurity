package com.encryption.monoalphabetic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MultiplicativeCipherTest {

    @Test
    void testEncryptionBasic() {
        String plaintext = "HELLO";
        int key = 5;

        String result = MultiplicativeCipher.encrypt(plaintext, key);

        assertAll("Encryption checks",
            () -> assertNotNull(result),
            () -> assertEquals(5, result.length()),
            () -> assertTrue(result.matches("[A-Z]+")),
            () -> assertEquals("JUDDS", result) // precomputed
        );
    }

    @Test
    void testDecryptionBasic() {
        String ciphertext = "JUDDS";
        int key = 5;

        String result = MultiplicativeCipher.decrypt(ciphertext, key);

        assertAll("Decryption checks",
            () -> assertEquals("HELLO", result),
            () -> assertFalse(result.contains(" "))
        );
    }

    @Test
    void testEncryptDecryptCycle() {
        String plaintext = "MULTIPLICATIVE";
        int key = 7;

        String encrypted = MultiplicativeCipher.encrypt(plaintext, key);
        String decrypted = MultiplicativeCipher.decrypt(encrypted, key);

        assertAll("Cycle consistency",
            () -> assertNotEquals(plaintext, encrypted),
            () -> assertEquals(plaintext, decrypted)
        );
    }

    @Test
    void testInvalidKey() {
        int invalidKey = 13; // not coprime with 26

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            MultiplicativeCipher.encrypt("HELLO", invalidKey);
        });

        assertTrue(ex.getMessage().contains("coprime"));
    }

    @Test
    void testCaseAndSpacesHandling() {
        String plaintext = "hello world";
        int key = 11;

        String encrypted = MultiplicativeCipher.encrypt(plaintext, key);
        String decrypted = MultiplicativeCipher.decrypt(encrypted, key);

        assertAll("Normalization checks",
            () -> assertEquals("HELLOWORLD", decrypted),
            () -> assertTrue(encrypted.equals(encrypted.toUpperCase()))
        );
    }

    @Test
    void testEmptyInput() {
        assertAll("Empty input",
            () -> assertEquals("", MultiplicativeCipher.encrypt("", 5)),
            () -> assertEquals("", MultiplicativeCipher.decrypt("", 5))
        );
    }
}