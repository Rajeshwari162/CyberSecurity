package com.encryption.polygraphic;
import java.util.*;

public class Playfair {

    private char[][] matrix = new char[5][5];
    private Map<Character, int[]> positionMap = new HashMap<>();

    public Playfair(String key) {
        generateMatrix(key);
    }
    
    private void generateMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        LinkedHashSet<Character> set = new LinkedHashSet<>();

        for (char c : key.toCharArray()) {
            set.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J') {
                set.add(c);
            }
        }

        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                char c = it.next();
                matrix[i][j] = c;
                positionMap.put(c, new int[]{i, j});
            }
        }
    }

    private String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                sb.append('X');
            }
        }

        if (sb.length() % 2 != 0) {
            sb.append('X');
        }

        return sb.toString();
    }

    public String encrypt(String plaintext) {
        String text = prepareText(plaintext);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] posA = positionMap.get(a);
            int[] posB = positionMap.get(b);

            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }

        return result.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);

            int[] posA = positionMap.get(a);
            int[] posB = positionMap.get(b);

            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + 4) % 5]);
                result.append(matrix[posB[0]][(posB[1] + 4) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + 4) % 5][posA[1]]);
                result.append(matrix[(posB[0] + 4) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]);
                result.append(matrix[posB[0]][posA[1]]);
            }
        }

        return result.toString();
    }
}