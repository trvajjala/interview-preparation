package com.innominds;

/**
 * Write a method that takes two integer values and returns true if the first value has more<br>
 * bits set than the second in its two’s-complement binary representation.
 *
 * @author ThirupathiReddy V
 *
 */
public class BitCountTest {

    public static void main(String[] args) {

        System.out.println(hasMoreBitSet(10, 02));
    }

    static boolean hasMoreBitSet(int a, int b) {

        return Integer.bitCount(a) > Integer.bitCount(b);
    }

}
