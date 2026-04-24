package com.encryption.monoalphabetic;

public class MultiplicativeCipher {

    private static final int MOD = 26;

    // Check if key is valid (must be coprime with 26)
    private static boolean isValidKey(int key) {
        return gcd(key, MOD) == 1;
    }

    private static int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    // Find modular inverse of key under mod 26
    private static int modInverse(int key) {
        key = key % MOD;
        for (int i = 1; i < MOD; i++) {
            if ((key * i) % MOD == 1) {
                return i;
            }
        }
        throw new IllegalArgumentException("No modular inverse for key: " + key);
    }

    public static String encrypt(String plaintext, int key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key must be coprime with 26");
        }

        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder result = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            int p = ch - 'A';
            char c = (char) ((p * key) % MOD + 'A');
            result.append(c);
        }

        return result.toString();
    }

    public static String decrypt(String ciphertext, int key) {
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key must be coprime with 26");
        }
        
        int inverse = modInverse(key);
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder result = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            int c = ch - 'A';
            char p = (char) ((c * inverse) % MOD + 'A');
            result.append(p);
        }

        return result.toString();
    }
}
