package com.encryption.transpositional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColumnarCipherTest {

    @Test
    void testEncryption() {
        ColumnarCipher.Result res =
                ColumnarCipher.encryptWithSteps("HELLO", "KEY");

        assertNotNull(res.output);
    }

    @Test
    void testStepsNotEmpty() {
        ColumnarCipher.Result res =
                ColumnarCipher.encryptWithSteps("HELLO", "KEY");

        assertFalse(res.steps.isEmpty());
    }

    @Test
    void testStepsContainGrid() {
        ColumnarCipher.Result res =
                ColumnarCipher.encryptWithSteps("HELLO", "KEY");

        assertTrue(res.steps.get(0).contains("Grid"));
    }

    @Test
    void testNotEqualsOriginal() {
        ColumnarCipher.Result res =
                ColumnarCipher.encryptWithSteps("HELLO", "KEY");

        assertNotEquals("HELLO", res.output);
    }

    @Test
    void testArrayEquals() {
        String result = ColumnarCipher.encryptWithSteps("HELLO", "KEY").output;
        assertArrayEquals(result.split(""), result.split(""));
    }

    @Test
    void testException() {
        assertThrows(IllegalArgumentException.class, () -> {
            ColumnarCipher.encryptWithSteps("HELLO", "");
        });
    }

    @Test
    void testWithPadding() {
        ColumnarCipher.Result res =
                ColumnarCipher.encryptWithSteps("HELLO", "ABCD");

        assertTrue(res.output.length() % 4 == 0);
    }
}
