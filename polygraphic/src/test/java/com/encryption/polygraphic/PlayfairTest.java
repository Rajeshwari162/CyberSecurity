package com.encryption.polygraphic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PlayfairTest {

    // setUp
    @BeforeEach
    void setUp() {
        System.out.println("Test Started...");
    }

    // tearDown
    @AfterEach
    void tearDown() {
        System.out.println("Test Finished...");
    }

    @Test
    void testEncryptionBasic() {
        Playfair cipher = new Playfair("MONARCHY");
        String encrypted = cipher.encrypt("INSTRUMENTS");
        assertEquals("GATLMZCLRQXA", encrypted);
    }

    @Test
    void testDecryptionBasic() {
        Playfair cipher = new Playfair("MONARCHY");
        String decrypted = cipher.decrypt("GATLMZCLRQXA");
        assertEquals("INSTRUMENTSX", decrypted);
    }

    @Test
    void testRepeatedLetters() {
        Playfair cipher = new Playfair("KEYWORD");
        String encrypted = cipher.encrypt("BALLOON");
        assertTrue(encrypted.length() % 2 == 0);
    }

    @Test
    void testOddLength() {
        Playfair cipher = new Playfair("SECRET");
        String encrypted = cipher.encrypt("HELLO");
        assertTrue(encrypted.length() % 2 == 0);
    }

    @Test
    void testEncryptDecryptCycle() {
        Playfair cipher = new Playfair("CRYPTO");
        String text = "HELLOWORLD";
        String encrypted = cipher.encrypt(text);
        String decrypted = cipher.decrypt(encrypted);
        assertTrue(decrypted.startsWith("HELXLOWORLD".substring(0, text.length())));
    }
}