package com.tvajjala.questions;

/**
 * @formatter:off
 *
 *  Worst case = O(n^3)
 *  Best case = O(n^2) sort the array before
 *
 * @formatter:on
 * @author ThirupathiReddy V
 */
public class SumOfThreeDigits {

    public static void main( String[] args ) {

        final int[] input = new int[] { 12, 3, -15, 83, 99, 12, 8, -20 };

        for ( int i = 0; i < input.length - 2; i++ ) {

            for ( int j = i + 1; j < input.length - 1; j++ ) {

                for ( int k = j + 1; k < input.length; k++ ) {

                    if ( input[i] + input[j] + input[k] == 0 ) {
                        System.out.println( String.format( "Elements %d , %d and  %d whose sum is equals to zero ", input[i], input[j], input[k] ) );
                        return;// comment this line to get all possible combinations
                    }
                }
            }
        }

    }
}
