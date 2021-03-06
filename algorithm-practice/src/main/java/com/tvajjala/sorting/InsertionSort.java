package com.tvajjala.sorting;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

	final int[] array = { 2, 9, 121, 10, 222, 34, 33333 };

	Arrays.stream(doInsertionSort(array)).forEach(i -> System.out.print(i + " "));
    }

    public static int[] doInsertionSort(int[] array) {

	for (int i = 1; i < array.length; i++) {

	    for (int j = i; j > 0; j--) {

		if (array[j - 1] > array[j]) {
		    final int temp = array[j - 1];
		    array[j - 1] = array[j];
		    array[j] = temp;
		}

	    }
	}

	return array;
    }
}
