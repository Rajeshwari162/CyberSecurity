package com.encryption.app;

import java.util.Scanner;

import com.encryption.monoalphabetic.*;
import com.encryption.polyalphabetic.*;
import com.encryption.polygraphic.*;
import com.encryption.transpositional.*;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== Encryption System ===");
            System.out.println("1. Caesar Cipher");
            System.out.println("2. Multiplicative Cipher");
            System.out.println("3. Vigenere Cipher");
            System.out.println("4. Autokey Cipher");
            System.out.println("5. Playfair Cipher");
            System.out.println("6. Hill Cipher");
            System.out.println("7. Rail Fence Cipher");
            System.out.println("8. Columnar Cipher");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 9) {
                System.out.println("Exiting...");
                return;
            }

            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.print("Choose operation: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: // Caesar
                    System.out.print("Enter text: ");
                    String t1 = sc.nextLine();
                    System.out.print("Enter shift: ");
                    int s1 = sc.nextInt();

                    if (op == 1)
                        System.out.println("Result: " + CaesarCipher.encrypt(t1, s1));
                    else
                        System.out.println("Result: " + CaesarCipher.decrypt(t1, s1));
                    break;

                case 2: // Multiplicative
                    System.out.print("Enter text: ");
                    String t2 = sc.nextLine();
                    System.out.print("Enter key: ");
                    int k2 = sc.nextInt();

                    if (op == 1)
                        System.out.println("Result: " + MultiplicativeCipher.encrypt(t2, k2));
                    else
                        System.out.println("Result: " + MultiplicativeCipher.decrypt(t2, k2));
                    break;

                case 3: // Vigenere
                    System.out.print("Enter text: ");
                    String t3 = sc.nextLine();
                    System.out.print("Enter key: ");
                    String k3 = sc.nextLine();

                    if (op == 1)
                        System.out.println("Result: " + VigenereCipher.encrypt(t3, k3));
                    else
                        System.out.println("Result: " + VigenereCipher.decrypt(t3, k3));
                    break;

                case 4: // Autokey
                    System.out.print("Enter text: ");
                    String t4 = sc.nextLine();
                    System.out.print("Enter key: ");
                    String k4 = sc.nextLine();

                    if (op == 1)
                        System.out.println("Result: " + AutokeyCipher.encrypt(t4, k4));
                    else
                        System.out.println("Result: " + AutokeyCipher.decrypt(t4, k4));
                    break;

                case 5: // Playfair
                    System.out.print("Enter key: ");
                    String k5 = sc.nextLine();
                    Playfair pf = new Playfair(k5);

                    System.out.print("Enter text: ");
                    String t5 = sc.nextLine();

                    if (op == 1)
                        System.out.println("Result: " + pf.encrypt(t5));
                    else
                        System.out.println("Result: " + pf.decrypt(t5));
                    break;

                case 6: // Hill
                    int[][] matrix = {{3, 3}, {2, 5}};

                    System.out.print("Enter text: ");
                    String t6 = sc.nextLine();

                    if (op == 1)
                        System.out.println("Result: " + HillCipher.encrypt(t6, matrix));
                    else
                        System.out.println("Result: " + HillCipher.decrypt(t6, matrix));
                    break;

                case 7: // Rail Fence
                    System.out.print("Enter text: ");
                    String t7 = sc.nextLine();
                    System.out.print("Enter key: ");
                    int k7 = sc.nextInt();

                    if (op == 1)
                        System.out.println("Result: " + RailFenceCipher.encrypt(t7, k7));
                    else
                        System.out.println("Result: " + RailFenceCipher.decrypt(t7, k7));
                    break;

                case 8: // Columnar
                    System.out.print("Enter text: ");
                    String t8 = sc.nextLine();
                    System.out.print("Enter key: ");
                    String k8 = sc.nextLine();

                    if (op == 1)
                        System.out.println("Result: " + ColumnarCipher.encrypt(t8, k8));
                    else
                        System.out.println("Result: " + ColumnarCipher.decrypt(t8, k8));
                    break;

                default:
                    System.out.println("Invalid choice!");
                    sc.close();
            }
        }
    }
}