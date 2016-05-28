package com.innominds.files;

import java.util.Scanner;

public class Exam {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of test cases");

        int testCount = 0;
        try {
            testCount = Integer.parseInt(scanner.nextLine());
        } catch (final NumberFormatException e) {
            System.err.println("Please input positive input number  ");
        }
        final String[] testCases = new String[testCount];

        for (int i = 0; i < testCount; i++) {
            System.out.printf("Enter testCase %d", i + 1);
            testCases[i] = scanner.nextLine();
        }

        scanner.close();
        for (final String testInput : testCases) {
            validateTestCase(testInput);
        }

    }

    static void validateTestCase(String testInput) {

        if (testInput.length() % 2 != 0) {
            System.out.println("NO");
            return;
        }

        boolean isValid = true;

        final int middleIndex = testInput.length() / 2;
        final char[] firstHalf = testInput.substring(0, middleIndex).toCharArray();
        final char[] secondHalf = testInput.substring(middleIndex, testInput.length()).toCharArray();

        for (int i = 0; i < firstHalf.length; i++) {

            final int c = firstHalf[i];
            final int n = getOpposite(secondHalf[secondHalf.length - (i + 1)]);

            if (c != n) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    static int getOpposite(int n) {
        if (n == 125) {
            n = 123;
        } else if (n == 93) {
            n = 91;
        } else if (n == 41) {
            n = 40;
        }

        return n;
    }
}
