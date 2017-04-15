package com.tvajjala.sorting;

import java.util.Arrays;

/**
 * @author: ThirupathiReddy V
 */
public class MergeSort {

    public static void main( String[] args ) {

        final int[] array = { 12, 8, 29, 33, 123, 92, -99 };

        Arrays.stream( doMergeSort( array ) ).forEach( i -> System.out.print( i + " " ) );

    }

    public static int[] doMergeSort( int[] array ) {

        if ( array.length < 2 ) {
            return array;
        }

        int[] left = new int[array.length / 2];

        int[] right = new int[array.length - left.length];

        System.arraycopy( array, 0, left, 0, left.length );
        System.arraycopy( array, left.length, right, 0, right.length );

        left = doMergeSort( left );
        right = doMergeSort( right );

        return merge( left, right );
    }

    public static int[] merge( int[] left, int[] right ) {

        final int[] sorted = new int[left.length + right.length];

        int leftCounter = 0;
        int rightCounter = 0;
        int sortedCounter = 0;

        while ( leftCounter < left.length && rightCounter < right.length ) {

            if ( left[leftCounter] < right[rightCounter] ) {
                sorted[sortedCounter++] = left[leftCounter++];
            } else {
                sorted[sortedCounter++] = right[rightCounter++];
            }
        }

        while ( leftCounter < left.length ) {
            sorted[sortedCounter++] = left[leftCounter++];
        }

        while ( rightCounter < right.length ) {
            sorted[sortedCounter++] = right[rightCounter++];
        }

        return sorted;
    }

}