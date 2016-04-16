package com.trvajjala.algo.sorting;

import java.util.Arrays;

/**
 * This is an iterative algorithms
 */
public class BubbleSort {

    public static void main(String[] args) {
        final int[] array = { 3, 4, 5, 22, 9, 2, 44, 1212, 2222 };
        Arrays.stream(doBubbleSort(array)).forEach(System.out::print);
    }

    public static int[] doBubbleSort(int[] array) {
        final int m = array.length - 1;
        for (int i = m; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    final int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

}
