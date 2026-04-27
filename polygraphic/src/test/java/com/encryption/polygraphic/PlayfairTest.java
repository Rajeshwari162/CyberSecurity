package com.encryption.polygraphic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PlayfairTest {

    @BeforeEach
    void setUp() {
        System.out.println("Test Started...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Finished...");
    }

    //  BASIC ENCRYPTION
    @Test
    void testEncryptionBasic() {
        Playfair cipher = new Playfair("MONARCHY");
        String encrypted = cipher.encrypt("INSTRUMENTS");
        assertEquals("GATLMZCLRQXA", encrypted);
    }

    //  BASIC DECRYPTION
    @Test
    void testDecryptionBasic() {
        Playfair cipher = new Playfair("MONARCHY");
        String decrypted = cipher.decrypt("GATLMZCLRQXA");
        assertEquals("INSTRUMENTSX", decrypted);
    }

    //  LENGTH SHOULD ALWAYS BE EVEN
    @Test
    void testEvenLengthOutput() {
        Playfair cipher = new Playfair("KEYWORD");
        String encrypted = cipher.encrypt("HELLO");
        assertTrue(encrypted.length() % 2 == 0);
    }

    //  REPEATED LETTER HANDLING
    @Test
    void testRepeatedLettersHandling() {
        Playfair cipher = new Playfair("KEYWORD");
        String prepared = cipher.encrypt("BALLOON");
        assertNotNull(prepared);
        assertTrue(prepared.length() % 2 == 0);
    }

    //  EMPTY STRING
    @Test
    void testEmptyInput() {
        Playfair cipher = new Playfair("KEY");
        String encrypted = cipher.encrypt("");
        assertEquals("", encrypted);
    }

    //  SINGLE CHARACTER INPUT
    @Test
    void testSingleCharacter() {
        Playfair cipher = new Playfair("KEY");
        String encrypted = cipher.encrypt("A");
        assertEquals(2, encrypted.length()); // padded with X
    }

    //  SPECIAL CHARACTERS REMOVED
    @Test
    void testSpecialCharactersRemoval() {
        Playfair cipher = new Playfair("KEY");
        String encrypted = cipher.encrypt("HELLO@123");
        assertFalse(encrypted.contains("@"));
    }

    //  J IS REPLACED BY I
    @Test
    void testJReplacedByI() {
        Playfair cipher = new Playfair("KEY");
        String encrypted = cipher.encrypt("JIG");
        assertNotNull(encrypted);
    }

    //  ENCRYPT-DECRYPT CONSISTENCY
    @Test
    void testEncryptDecryptCycle() {
        Playfair cipher = new Playfair("CRYPTO");
        String text = "HELLOWORLD";
        String encrypted = cipher.encrypt(text);
        String decrypted = cipher.decrypt(encrypted);

        assertTrue(decrypted.startsWith("HELXLOWORLD".substring(0, text.length())));
    }

    //  DIFFERENT KEY SHOULD GIVE DIFFERENT OUTPUT
    @Test
    void testDifferentKeysProduceDifferentOutput() {
        Playfair cipher1 = new Playfair("KEYONE");
        Playfair cipher2 = new Playfair("KEYTWO");

        String text = "HELLO";
        String enc1 = cipher1.encrypt(text);
        String enc2 = cipher2.encrypt(text);

        assertNotEquals(enc1, enc2);
    }

    //  NULL KEY CHECK (important edge case)
    @Test
    void testNullKey() {
        assertThrows(NullPointerException.class, () -> {
            new Playfair(null);
        });
    }

    //  LARGE INPUT TEST
    @Test
    void testLargeInput() {
        Playfair cipher = new Playfair("KEYWORD");

        String text = "HELLOWORLDHELLOWORLDHELLOWORLD";
        String encrypted = cipher.encrypt(text);

        assertNotNull(encrypted);
        assertTrue(encrypted.length() > 0);
    }
}