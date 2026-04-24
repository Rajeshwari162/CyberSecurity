package com.encryption.monoalphabetic;
public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        if (shift < 0) {
            throw new IllegalArgumentException("Shift cannot be negative");
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isUpperCase(ch)) {
                char encrypted = (char) ((ch - 'A' + shift) % 26 + 'A');
                result.append(encrypted);
            } else if (Character.isLowerCase(ch)) {
                char encrypted = (char) ((ch - 'a' + shift) % 26 + 'a');
                result.append(encrypted);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
    if (shift < 0) {
        throw new IllegalArgumentException("Shift cannot be negative");
    }

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < text.length(); i++) {
        char ch = text.charAt(i);

        if (Character.isUpperCase(ch)) {
            char decrypted = (char) ((ch - 'A' - shift + 26) % 26 + 'A');
            result.append(decrypted);
        } else if (Character.isLowerCase(ch)) {
            char decrypted = (char) ((ch - 'a' - shift + 26) % 26 + 'a');
            result.append(decrypted);
        } else {
            result.append(ch);
        }
    }

    return result.toString();
}
}
