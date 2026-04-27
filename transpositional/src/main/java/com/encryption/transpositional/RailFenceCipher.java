package com.encryption.transpositional;

public class RailFenceCipher {

    public static String encrypt(String text, int key) {

        // ✅ Exception Handling
        if (text == null) {
            throw new NullPointerException("Text cannot be null");
        }

        if (key <= 0) {
            throw new IllegalArgumentException("Key must be greater than 0");
        }

        if (key == 1 || text.length() == 0) {
            return text;
        }

        char[][] rail = new char[key][text.length()];

        // Fill with newline
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                rail[i][j] = '\n';

        boolean dirDown = false;
        int row = 0, col = 0;

        // Create zig-zag pattern
        for (int i = 0; i < text.length(); i++) {

            if (row == 0 || row == key - 1)
                dirDown = !dirDown;

            rail[row][col++] = text.charAt(i);

            row += dirDown ? 1 : -1;
        }

        // Read matrix row-wise
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result.append(rail[i][j]);

        return result.toString();
    }

    public static String decrypt(String cipher, int key) {

        // ✅ Exception Handling
        if (cipher == null) {
            throw new NullPointerException("Cipher text cannot be null");
        }

        if (key <= 0) {
            throw new IllegalArgumentException("Key must be greater than 0");
        }

        if (key == 1 || cipher.length() == 0) {
            return cipher;
        }

        char[][] rail = new char[key][cipher.length()];

        // Fill with newline
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                rail[i][j] = '\n';

        boolean dirDown = false;
        int row = 0, col = 0;

        // Mark zig-zag pattern
        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0) dirDown = true;
            if (row == key - 1) dirDown = false;

            rail[row][col++] = '*';
            row += dirDown ? 1 : -1;
        }

        // Fill characters
        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*' && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);

        // Read zig-zag
        StringBuilder result = new StringBuilder();
        row = 0;
        col = 0;

        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0) dirDown = true;
            if (row == key - 1) dirDown = false;

            result.append(rail[row][col++]);
            row += dirDown ? 1 : -1;
        }

        return result.toString();
    }
}