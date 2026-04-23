package com.encryption.polygraphic;
public class HillCipher {

    // Multiply matrix
    static int[] multiply(int[][] matrix, int[] vector) {
        int[] result = new int[2];

        for (int i = 0; i < 2; i++) {
            result[i] = 0;
            for (int j = 0; j < 2; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26;
        }
        return result;
    }

    // Modular inverse
    static int modInverse(int det) {
        det %= 26;
        for (int i = 1; i < 26; i++) {
            if ((det * i) % 26 == 1)
                return i;
        }
        return -1;
    }

    // Encryption
    public static String encrypt(String message, int[][] key) {
        message = message.toUpperCase().replaceAll("[^A-Z]", "");

        if (message.length() % 2 != 0)
            message += "X";  // Padding

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < message.length(); i += 2) {
            int[] vector = {
                message.charAt(i) - 'A',
                message.charAt(i + 1) - 'A'
            };

            int[] result = multiply(key, vector);

            cipher.append((char)(result[0] + 'A'));
            cipher.append((char)(result[1] + 'A'));
        }

        return cipher.toString();
    }

    // Decryption
    public static String decrypt(String cipherText, int[][] key) {

        int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
        det = ((det % 26) + 26) % 26;

        int invDet = modInverse(det);

        if (invDet == -1)
            return "Invalid Key Matrix (Not Invertible)";

        int[][] inverseKey = new int[2][2];

        inverseKey[0][0] = key[1][1];
        inverseKey[1][1] = key[0][0];
        inverseKey[0][1] = -key[0][1];
        inverseKey[1][0] = -key[1][0];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverseKey[i][j] =
                        ((inverseKey[i][j] * invDet) % 26 + 26) % 26;
            }
        }

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i += 2) {
            int[] vector = {
                cipherText.charAt(i) - 'A',
                cipherText.charAt(i + 1) - 'A'
            };

            int[] result = multiply(inverseKey, vector);

            plain.append((char)(result[0] + 'A'));
            plain.append((char)(result[1] + 'A'));
        }

        return plain.toString();
    }

    // MAIN METHOD (Hardcoded values)
    public static void main(String[] args) {

        // ✅ Valid Key Matrix
        int[][] key = {
            {3, 3},
            {2, 5}
        };

        String message = "HELLO";

        System.out.println("Original Message: " + message);

        String encrypted = encrypt(message, key);
        System.out.println("Encrypted Message: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Message: " + decrypted);
    }
}