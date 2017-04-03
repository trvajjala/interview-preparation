package com.tvajjala.searching;

public class LinearSearch {

    public static void main(String[] args) {

        final int[] array = { 2, 99, 122, 222, 11 };

        System.out.println("Search found at : " + doLinearSearch(array, 11));

    }

    public static int doLinearSearch(int[] array, int target) {

        for (int i = 0; i < array.length; i++) {

            if (array[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
