package com.encryption.polyalphabetic;

public class AutokeyCipher {

    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase();

        StringBuilder fullKey = new StringBuilder(key);

        for (int i = 0; fullKey.length() < plaintext.length(); i++) {
            fullKey.append(plaintext.charAt(i));
        }

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            int p = plaintext.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';

            char c = (char) ((p + k) % 26 + 'A');
            ciphertext.append(c);
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase();

        StringBuilder fullKey = new StringBuilder(key);
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            int c = ciphertext.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';

            char p = (char) ((c - k + 26) % 26 + 'A');
            plaintext.append(p);

            fullKey.append(p);
        }

        return plaintext.toString();
    }
}