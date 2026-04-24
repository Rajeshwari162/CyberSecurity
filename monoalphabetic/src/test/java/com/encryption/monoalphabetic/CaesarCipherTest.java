package com.encryption.monoalphabetic;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CaesarCipherTest {

    CaesarCipher cipher;

    // setUp
    @BeforeEach
    void setUp() {
        cipher = new CaesarCipher();
        System.out.println("Test Started...");
    }

    // tearDown
    @AfterEach
    void tearDown() {
        cipher = null;
        System.out.println("Test Finished...");
    }

    @Test
    void testEncryption() {
        String result = CaesarCipher.encrypt("HELLO", 3);
        assertEquals("KHOOR", result);
    }

    @Test
    void testAssertTrue() {
        String result = CaesarCipher.encrypt("ABC", 1);
        assertTrue(result.equals("BCD"));
    }

    @Test
    void testAssertFalse() {
        String result = CaesarCipher.encrypt("ABC", 1);
        assertFalse(result.equals("XYZ"));
    }

    @Test
    void testNotEquals() {
        String result = CaesarCipher.encrypt("HELLO", 2);
        assertNotEquals("HELLO", result);
    }

    @RepeatedTest(3)
    void repeatTest() {
        String result = CaesarCipher.encrypt("JAVA", 1);
        assertEquals("KBWB", result);
    }

    @Test
    void testException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CaesarCipher.encrypt("HELLO", -1);
        });

        assertTrue(exception.getMessage().contains("Shift"));
    }

    @Test
void testDecryption() {
    String encrypted = CaesarCipher.encrypt("RAJESHWARI", 3);
    String decrypted = CaesarCipher.decrypt(encrypted, 3);

    assertEquals("RAJESHWARI", decrypted);
}
}
