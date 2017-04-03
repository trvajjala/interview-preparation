package com.trvajjala.algo.test;

import org.junit.Assert;
import org.junit.Test;

import com.tvajjala.sorting.BubbleSort;
import com.tvajjala.sorting.MergeSort;
import com.tvajjala.sorting.SelectionSort;

public class SortingAlgoTest {

    @Test
    public void mergeSortTest() {

        final int[] array = { 2, 9, 3, 10 };

        final int[] sorted = MergeSort.doMergeSort(array);

        Assert.assertArrayEquals(new int[] { 2, 3, 9, 10 }, sorted);
    }

    @Test
    public void selectionSortTest() {

        final int[] array = { 2, 9, 3, 10 };

        final int[] sorted = SelectionSort.doSelectionSort(array);

        Assert.assertArrayEquals(new int[] { 2, 3, 9, 10 }, sorted);

    }

    @Test
    public void bubbleSortTest() {

        final int[] array = { 2, 9, 3, 10 };

        final int[] sorted = BubbleSort.doBubbleSort(array);

        Assert.assertArrayEquals(new int[] { 2, 3, 9, 10 }, sorted);

    }

}