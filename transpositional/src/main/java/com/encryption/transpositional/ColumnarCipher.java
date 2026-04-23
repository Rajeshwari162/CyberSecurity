package com.encryption.transpositional;

import java.util.*;

public class ColumnarCipher {

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
        text = text.replaceAll(" ", "").toUpperCase();
        String upperKey = key.toUpperCase();

        int cols = upperKey.length();
        int rows = (int) Math.ceil((double) text.length() / cols);

        char[][] grid = new char[rows][cols];

        // Fill grid row-wise
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (index < text.length()) {
                    grid[i][j] = text.charAt(index++);
                } else {
                    grid[i][j] = 'X'; // padding
                }
            }
        }

        steps.add("Grid formed:");
        for (char[] row : grid) {
            steps.add(Arrays.toString(row));
        }

        // Determine column order
        Integer[] order = new Integer[cols];
        for (int i = 0; i < cols; i++) order[i] = i;

        Arrays.sort(order, Comparator.comparingInt(i -> upperKey.charAt(i)));

        steps.add("Column order based on key: " + Arrays.toString(order));

        // Read column-wise
        StringBuilder result = new StringBuilder();
        for (int col : order) {
            for (int i = 0; i < rows; i++) {
                result.append(grid[i][col]);
            }
        }

        steps.add("Final encrypted text: " + result.toString());

        return new Result(result.toString(), steps);
    }

    // ✅ OPTIONAL: DECRYPT METHOD
    public static String decrypt(String cipherText, String key) {

        int cols = key.length();
        int rows = cipherText.length() / cols;

        char[][] grid = new char[rows][cols];

        Integer[] order = new Integer[cols];
        for (int i = 0; i < cols; i++) order[i] = i;

        Arrays.sort(order, Comparator.comparingInt(i -> key.charAt(i)));

        int index = 0;
        for (int col : order) {
            for (int i = 0; i < rows; i++) {
                grid[i][col] = cipherText.charAt(index++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.append(grid[i][j]);
            }
        }

        return result.toString();
    }
}