package com.trvajjala.algo.sorting;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[] array = { 20, 412, 5, 7, 2992, 122, 3000, 333, 444, 999 };

        final int start = 0;
        final int end = array.length - 1;

        array = doQuickSort(array, start, end);

        Arrays.stream(array).forEach(i -> System.out.print(i + " "));

    }

    public static int[] doQuickSort(int[] array, int start, int end) {

        int i = start;
        int j = end;
        final int pivot = array[i + (j - i) / 2];

        while (i <= j) {

            while (array[i] < pivot) {
                i++;
            }

            while (array[j] > pivot) {
                j--;
            }

            while (i <= j) {

                final int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;

                i++;
                j--;
            }
        }

        if (start < j) {
            doQuickSort(array, start, j);
        }

        if (i < end) {
            doQuickSort(array, i, end);
        }

        return array;
    }

}
