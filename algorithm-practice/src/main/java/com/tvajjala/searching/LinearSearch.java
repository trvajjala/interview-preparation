package com.tvajjala.searching;
/**
 *
 * @author ThirupathiReddy V
 *
 */
public class LinearSearch {

    public static void main(String[] args) {

        final int[] array = { 2, 99, 122, 222, 11 };

        final int target=11;

        System.out.printf("Search found at index : %d " , doLinearSearch(array, target));

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
