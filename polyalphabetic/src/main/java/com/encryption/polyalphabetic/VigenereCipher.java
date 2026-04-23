package com.encryption.polyalphabetic;

import java.util.ArrayList;
import java.util.List;

public class VigenereCipher {

    // Result class (for steps)
    public static class Result {
        public String output;
        public List<String> steps;

        public Result(String output, List<String> steps) {
            this.output = output;
            this.steps = steps;
        }
    }

    // ✅ SIMPLE METHOD (for MainApp)
    public static String encrypt(String text, String key) {
        return encryptWithSteps(text, key).output;
    }

    // ✅ METHOD WITH STEPS
    public static Result encryptWithSteps(String text, String key) {

        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty");
        }

        List<String> steps = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        key = key.toUpperCase();
        text = text.toUpperCase();

        int j = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                int shift = key.charAt(j % key.length()) - 'A';
                char encrypted = (char) ((c - 'A' + shift) % 26 + 'A');

                steps.add("Char: " + c +
                        " | Key: " + key.charAt(j % key.length()) +
                        " | Shift: " + shift +
                        " | Result: " + encrypted);

                result.append(encrypted);
                j++;
            } else {
                steps.add("Non-letter: " + c);
                result.append(c);
            }
        }

        return new Result(result.toString(), steps);
    }

    // ✅ OPTIONAL: DECRYPT METHOD
    public static String decrypt(String cipherText, String key) {

        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty");
        }

        StringBuilder result = new StringBuilder();

        key = key.toUpperCase();
        cipherText = cipherText.toUpperCase();

        int j = 0;

        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);

            if (Character.isLetter(c)) {
                int shift = key.charAt(j % key.length()) - 'A';
                char decrypted = (char) ((c - 'A' - shift + 26) % 26 + 'A');

                result.append(decrypted);
                j++;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }
}