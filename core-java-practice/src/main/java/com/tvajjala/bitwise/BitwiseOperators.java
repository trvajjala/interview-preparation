package com.tvajjala.bitwise;

public class BitwiseOperators {

    public static void main( String[] args ) {

        System.out.println( "Singed Leftshift" );
        // signed left shift
        System.out.println( Integer.toBinaryString( 8 ) );
        System.out.println( 8 << 3 );
        System.out.println( Integer.toBinaryString( 8 << 3 ) );

        System.out.println( "Signed right shift (empty cells filled with 1's for negative number. 0's for positive number)" );
        // signed right shift
        System.out.println( Integer.toBinaryString( -8 ) );
        System.out.println( -8 >> 3 );
        System.out.println( Integer.toBinaryString( -8 >> 3 ) );
        System.out.println( " For positve number" );
        System.out.println( Integer.toBinaryString( 8 ) );
        System.out.println( 8 >> 3 );
        System.out.println( Integer.toBinaryString( 8 >> 3 ) );

        System.out.println( "unsigned right shif always filled with 0's" );
        // unsigned right shift
        System.out.println( Integer.toBinaryString( -8 ) );
        System.out.println( -8 >>> 3 );
        System.out.println( Integer.toBinaryString( -8 >>> 3 ) );

        System.out.println( Integer.toString( 8, 2 ) );

    }
}
