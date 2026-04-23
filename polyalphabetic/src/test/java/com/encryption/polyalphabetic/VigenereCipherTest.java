package com.encryption.polyalphabetic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VigenereCipherTest {

    @Test
    void testEncryption() {
        VigenereCipher.Result res =
                VigenereCipher.encryptWithSteps("HELLO", "KEY");

        assertEquals("RIJVS", res.output);
    }

    @Test
    void testStepsSize() {
        VigenereCipher.Result res =
                VigenereCipher.encryptWithSteps("HELLO", "KEY");

        assertEquals(5, res.steps.size());
    }

    @Test
    void testStepsNotEmpty() {
        VigenereCipher.Result res =
                VigenereCipher.encryptWithSteps("HELLO", "KEY");

        assertFalse(res.steps.isEmpty());
    }

    @Test
    void testContainsStepData() {
        VigenereCipher.Result res =
                VigenereCipher.encryptWithSteps("H", "K");

        assertTrue(res.steps.get(0).contains("Shift"));
    }

    @Test
    void testNotNull() {
        VigenereCipher.Result res =
                VigenereCipher.encryptWithSteps("HELLO", "KEY");

        assertNotNull(res);
    }

    @Test
    void testNotEquals() {
        VigenereCipher.Result res =
                VigenereCipher.encryptWithSteps("HELLO", "KEY");

        assertNotEquals("HELLO", res.output);
    }

    @Test
    void testArrayEquals() {
        String[] expected = {"R","I","J","V","S"};
        String result = VigenereCipher.encryptWithSteps("HELLO","KEY").output;

        assertArrayEquals(expected, result.split(""));
    }

    @Test
    void testException() {
        assertThrows(IllegalArgumentException.class, () -> {
            VigenereCipher.encryptWithSteps("HELLO", "");
        });
    }
}
