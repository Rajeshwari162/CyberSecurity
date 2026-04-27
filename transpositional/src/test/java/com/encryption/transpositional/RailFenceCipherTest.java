package com.encryption.transpositional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RailFenceCipherTest {

    //  Encryption Test
    @Test
    void testEncryption() {
        assertEquals("HOLELWRDLO",
                RailFenceCipher.encrypt("HELLOWORLD", 3));
    }

    //  Decryption Test
    @Test
    void testDecryption() {
        assertEquals("HELLOWORLD",
                RailFenceCipher.decrypt("HOLELWRDLO", 3));
    }

    //  assertTrue Example
    @Test
    void testContainsCharacter() {
        String result = RailFenceCipher.encrypt("HELLO", 3);
        assertTrue(result.contains("H"));
    }

    //  assertFalse Example
    @Test
    void testDoesNotContainCharacter() {
        String result = RailFenceCipher.encrypt("HELLO", 3);
        assertFalse(result.contains("Z"));
    }

    //  assertNotEquals Example
    @Test
    void testNotEqual() {
        assertNotEquals("HELLOWORLD",
                RailFenceCipher.encrypt("HELLOWORLD", 3));
    }

    //  Single Rail Case
    @Test
    void testSingleRail() {
        assertEquals("JAVA",
                RailFenceCipher.encrypt("JAVA", 1));
    }

    //  Empty String
    @Test
    void testEmptyText() {
        assertEquals("",
                RailFenceCipher.encrypt("", 3));
    }

    //  Exception: Invalid Key
    @Test
    void testInvalidKey() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            RailFenceCipher.encrypt("HELLO", 0);
        });

        assertEquals("Key must be greater than 0", exception.getMessage());
    }

    //  Exception: Null Input
    @Test
    void testNullInput() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            RailFenceCipher.encrypt(null, 3);
        });

        assertEquals("Text cannot be null", exception.getMessage());
    }

    //  Encrypt → Decrypt Cycle
    @Test
    void testEncryptDecryptCycle() {
        String original = "COMPUTER";
        String encrypted = RailFenceCipher.encrypt(original, 3);
        String decrypted = RailFenceCipher.decrypt(encrypted, 3);

        assertEquals(original, decrypted);
    }

    //  Decrypt Null
    @Test
    void testDecryptNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            RailFenceCipher.decrypt(null, 3);
        });

        assertEquals("Cipher text cannot be null", exception.getMessage());
    }
}