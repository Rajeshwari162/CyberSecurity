package com.encryption.polygraphic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HillCipherTest {

    // ✅ Valid key matrix
    int[][] validKey = {
        {3, 3},
        {2, 5}
    };

    // ❌ Invalid key matrix
    int[][] invalidKey = {
        {2, 4},
        {2, 4}
    };

    // ✅ BASIC ENCRYPTION
    @Test
    void testEncryption() {
        String encrypted = HillCipher.encrypt("HELLO", validKey);
        assertEquals("HIOZHN", encrypted);
    }

    // ✅ BASIC DECRYPTION
    @Test
    void testDecryption() {
        String decrypted = HillCipher.decrypt("HIOZHN", validKey);
        assertEquals("HELLOX", decrypted); // padded X
    }

    // ✅ PADDING CHECK
    @Test
    void testPaddingForOddLength() {
        String encrypted = HillCipher.encrypt("ABC", validKey);
        assertEquals(4, encrypted.length());
    }

    // ✅ ENCRYPT-DECRYPT CONSISTENCY
    @Test
    void testEncryptDecryptCycle() {
        String text = "CRYPTO";

        String encrypted = HillCipher.encrypt(text, validKey);
        String decrypted = HillCipher.decrypt(encrypted, validKey);

        assertTrue(decrypted.startsWith(text));
    }

    // ✅ INVALID KEY MATRIX
    @Test
    void testInvalidKeyMatrix() {
        String result = HillCipher.decrypt("TEST", invalidKey);
        assertEquals("Invalid Key Matrix (Not Invertible)", result);
    }

    // ✅ CASE INSENSITIVITY
    @Test
    void testLowercaseInput() {
        String encrypted = HillCipher.encrypt("hello", validKey);
        assertEquals("HIOZHN", encrypted);
    }

    // ✅ REMOVE SPECIAL CHARACTERS
    @Test
    void testRemoveNonAlphabetCharacters() {
        String encrypted = HillCipher.encrypt("HELLO123!", validKey);
        assertEquals("HIOZHN", encrypted);
    }

    // ✅ DIFFERENT OUTPUT CHECK
    @Test
    void testDifferentOutput() {
        String encrypted = HillCipher.encrypt("WORLD", validKey);
        assertNotEquals("HIOZHN", encrypted);
    }

    // ✅ EMPTY STRING
    @Test
    void testEmptyInput() {
        String encrypted = HillCipher.encrypt("", validKey);
        assertEquals("", encrypted);
    }

    // ✅ SINGLE CHARACTER (should pad)
    @Test
    void testSingleCharacter() {
        String encrypted = HillCipher.encrypt("A", validKey);
        assertEquals(2, encrypted.length());
    }

    // ✅ LARGE INPUT
    @Test
    void testLargeInput() {
        String text = "HELLOWORLDHELLOWORLD";

        String encrypted = HillCipher.encrypt(text, validKey);

        assertNotNull(encrypted);
        assertTrue(encrypted.length() > 0);
    }

    // ✅ NULL INPUT CHECK
    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            HillCipher.encrypt(null, validKey);
        });
    }

    // ✅ MATRIX MULTIPLICATION CHECK
    @Test
    void testMatrixMultiplication() {
        int[][] key = {
            {1, 2},
            {3, 4}
        };

        int[] vector = {1, 1};

        int[] result = HillCipher.multiply(key, vector);

        assertArrayEquals(new int[]{3, 7}, result);
    }

    // ✅ MODULAR INVERSE CHECK
    @Test
    void testModInverseValid() {
        int inv = HillCipher.modInverse(15); // inverse of 15 mod 26 = 7
        assertEquals(7, inv);
    }

    // ✅ MODULAR INVERSE INVALID
    @Test
    void testModInverseInvalid() {
        int inv = HillCipher.modInverse(13); // no inverse mod 26
        assertEquals(-1, inv);
    }
}