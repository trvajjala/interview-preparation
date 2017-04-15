package com.tvajjala.hashing;
/**
 * This program illustrates how the hashMap indexing is calculated and fitted into exact location inside buckets
 * 
 * @author ThirupathiReddy V
 *
 */
public class HashIndexing {

    public static void main(String[] args) {
        final int length = 10;

        final int hashcodes[] = { 283889292, 8388388, 9991919, 29929199, 1929929, 4939933, 19929292 };

        for (final int hashcode : hashcodes) {
            final int loc = hashcode & length - 1;
            System.out.println(loc);
        }

    }
}
