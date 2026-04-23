package com.encryption.polygraphic;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HillCipherTest {

    // Valid key matrix
    int[][] validKey = {
        {3, 3},
        {2, 5}
    };

    // Invalid key matrix (determinant not invertible mod 26)
    int[][] invalidKey = {
        {2, 4},
        {2, 4}
    };

    @Test
    void testEncryption() {
        String message = "HELLO";

        String encrypted = HillCipher.encrypt(message, validKey);

        assertNotNull(encrypted);
        assertEquals("HIOZHN", encrypted);
    }

    @Test
    void testDecryption() {
        String cipherText = "HIOZHN";

        String decrypted = HillCipher.decrypt(cipherText, validKey);

        assertNotNull(decrypted);
        assertEquals("HELLOX", decrypted); // X due to padding
    }

    @Test
    void testEncryptionWithPadding() {
        String message = "ABC"; // Odd length

        String encrypted = HillCipher.encrypt(message, validKey);

        assertEquals(4, encrypted.length()); // Should pad to even
    }

    @Test
    void testDecryptReturnsOriginalMessage() {
        String message = "CRYPTO";

        String encrypted = HillCipher.encrypt(message, validKey);
        String decrypted = HillCipher.decrypt(encrypted, validKey);

        assertTrue(decrypted.startsWith("CRYPTO"));
    }

    @Test
    void testInvalidKeyMatrix() {
        String cipherText = "TEST";

        String result = HillCipher.decrypt(cipherText, invalidKey);

        assertEquals("Invalid Key Matrix (Not Invertible)", result);
    }

    @Test
    void testCaseInsensitivity() {
        String message = "hello";

        String encrypted = HillCipher.encrypt(message, validKey);

        assertEquals("HIOZHN", encrypted);
    }

    @Test
    void testRemoveNonAlphabetCharacters() {
        String message = "HELLO123!";

        String encrypted = HillCipher.encrypt(message, validKey);

        assertEquals("HIOZHN", encrypted);
    }
}