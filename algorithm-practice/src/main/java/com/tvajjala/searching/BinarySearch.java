package com.tvajjala.searching;
/**
 *
 * @author ThirupathiReddy V
 *
 * Binary search works in sorted array
 *
 */
public class BinarySearch {

    public static void main( String[] args ) {

        final int[] array = { 2, 3, 4, 5, 8 };

        System.out.println( "search found item at index : " + doBinarySearch( array, 4, 0, array.length ) );
    }

    public static int doBinarySearch( int[] array, int target, int left, int right ) {

        final int pivot = ( left + right ) / 2;

        if ( array[pivot] == target ) {
            return pivot;
        } else if ( array[pivot] < target ) {
            return doBinarySearch( array, target, pivot + 1, array.length );
        } else {
            return doBinarySearch( array, target, 0, pivot );
        }

    }
}
