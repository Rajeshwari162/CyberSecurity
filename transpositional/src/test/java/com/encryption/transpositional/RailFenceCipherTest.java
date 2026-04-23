package com.encryption.transpositional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RailFenceCipherTest {

    @Test
    void testEncryption() {
        assertEquals("HOLELWRDLO",
                RailFenceCipher.encrypt("HELLOWORLD", 3));
    }

    @Test
    void testDecryption() {
        assertEquals("HELLOWORLD",
                RailFenceCipher.decrypt("HOLELWRDLO", 3));
    }

    @Test
    void testSingleRail() {
        assertEquals("JAVA",
                RailFenceCipher.encrypt("JAVA", 1));
    }

    @Test
    void testEmptyText() {
        assertEquals("",
                RailFenceCipher.encrypt("", 3));
    }
}
