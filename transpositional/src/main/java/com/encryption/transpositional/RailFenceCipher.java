package com.encryption.transpositional;

public class RailFenceCipher {

    public static String encrypt(String text, int key) {

        if (key <= 1) return text;

        char[][] rail = new char[key][text.length()];

        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                rail[i][j] = '\n';

        boolean dirDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {

            if (row == 0 || row == key - 1)
                dirDown = !dirDown;

            rail[row][col++] = text.charAt(i);
            row += dirDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result.append(rail[i][j]);

        return result.toString();
    }

    public static String decrypt(String cipher, int key) {

        if (key <= 1) return cipher;

        char[][] rail = new char[key][cipher.length()];

        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                rail[i][j] = '\n';

        boolean dirDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < cipher.length(); i++) {

            if (row == 0) dirDown = true;
            if (row == key - 1) dirDown = false;

            rail[row][col++] = '*';
            row += dirDown ? 1 : -1;
        }

        int index = 0;
        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (rail[i][j] == '*' && index < cipher.length())
                    rail[i][j] = cipher.charAt(index++);

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
