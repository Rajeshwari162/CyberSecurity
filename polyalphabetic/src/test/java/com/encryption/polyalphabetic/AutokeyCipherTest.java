package com.encryption.polyalphabetic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AutokeyCipherTest {

    @Test
    void testEncryptionBasic() {
        String plaintext = "HELLOWORLD";
        String key = "KEY";

        String result = AutokeyCipher.encrypt(plaintext, key);

        assertAll("Encryption checks",
            () -> assertNotNull(result),
            () -> assertEquals(10, result.length()),
            () -> assertTrue(result.matches("[A-Z]+")),
            () -> assertEquals("RIJSSHZFHR", result)
        );
    }

    @Test
    void testDecryptionBasic() {
        String ciphertext = "RIJSSHZFHR";
        String key = "KEY";

        String result = AutokeyCipher.decrypt(ciphertext, key);

        assertAll("Decryption checks",
            () -> assertNotNull(result),
            () -> assertEquals("HELLOWORLD", result),
            () -> assertFalse(result.contains(" "))
        );
    }

    @Test
    void testEncryptDecryptCycle() {
        String plaintext = "AUTOKEYCIPHER";
        String key = "SECRET";

        String encrypted = AutokeyCipher.encrypt(plaintext, key);
        String decrypted = AutokeyCipher.decrypt(encrypted, key);

        assertAll("Cycle consistency",
            () -> assertNotEquals(plaintext, encrypted), // should change
            () -> assertEquals(plaintext, decrypted)
        );
    }

    @Test
    void testCaseNormalization() {
        String plaintext = "hello world";
        String key = "key";

        String encrypted = AutokeyCipher.encrypt(plaintext, key);

        assertTrue(encrypted.equals(encrypted.toUpperCase()));
    }

    @Test
    void testEmptyInput() {
        assertAll("Empty cases",
            () -> assertEquals("", AutokeyCipher.encrypt("", "KEY")),
            () -> assertEquals("", AutokeyCipher.decrypt("", "KEY"))
        );
    }
}