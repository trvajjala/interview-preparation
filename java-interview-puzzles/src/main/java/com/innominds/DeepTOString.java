package com.innominds;

import java.util.Arrays;

/**
 * The method is provided for you in release 5.0 and later releases, and is called Arrays.deepToString. If you pass it an array of object references, it returns
 * a nice string representation. It can deal with nested arrays and even circular references, where an array element refers to the enclosing array, directly or
 * indirectly. In fact, the Arrays class in release 5.0 provides a whole family of toString, equals, and hashCode methods that allow you to print, compare, or
 * hash the contents of any array of primitives or object references.
 *
 *
 * @author ThirupathiReddy V
 *
 */
public class DeepTOString {

    public static void main(String[] args) {

        final int[][][] str = { { { 2 }, { 4 }, { 4 } }, { { 3 }, { 4 }, { 22 } } };

        System.out.println(Arrays.deepToString(str));

    }

}
