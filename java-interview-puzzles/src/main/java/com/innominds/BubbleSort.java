package com.innominds;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        final Integer[] arr = { 2, 3, 7, 05, 0, 11 };

        System.out.println(Arrays.deepToString(insertionSort(arr)));
    }

    public static Integer[] bubbleSort(Integer[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {

                if (arr[j] > arr[j + 1]) {

                    final int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                }

            }
        }
        return arr;
    }

    public static Integer[] insertionSort(Integer[] arr) {

        for (int i = 1; i < arr.length - 1; i++) {

            for (int j = i; j > 0; j--) {

                if (arr[j - 1] > arr[j]) {
                    final int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }

        return arr;
    }

    public static Integer[] selectionSort(Integer[] data) {

        for (int i = 0; i < data.length - 1; i++) {
            int mvi = i;

            for (int j = i + 1; j < data.length; j++) {
                if (data[mvi] > data[j]) {
                    mvi = j;
                }
            }

            final int tmp = data[mvi];
            data[mvi] = data[i];
            data[i] = tmp;

        }

        return data;
    }
}
