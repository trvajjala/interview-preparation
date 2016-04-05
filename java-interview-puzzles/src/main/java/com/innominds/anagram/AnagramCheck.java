package com.innominds.anagram;

import java.util.Arrays;

/**
 * An anagram of a string is another string that contains same characters, only the order of characters can be different.<br>
 * For example, “abcd” and “dabc” are anagram of each other.
 *
 * @author ThirupathiReddy V
 *
 */
public class AnagramCheck {

    public static void main(String[] args) {
        System.out.println(isAnagram2("abcd", "acbd"));
    }

    static boolean isAnagram(String source, String anagram) {

        if (source == null) {
            throw new IllegalArgumentException();
        }
        if (source.length() != anagram.length()) {
            return false;
        }

        final char[] srcArray = source.toCharArray();
        final char[] anagramArray = anagram.toCharArray();

        Arrays.sort(srcArray);
        Arrays.sort(anagramArray);

        return Arrays.equals(srcArray, anagramArray);

    }

    static boolean isAnagram2(String src, String anagram) {
        if (src == null) {
            throw new IllegalArgumentException();
        }

        if (src.length() != anagram.length()) {
            return false;
        }

        final char[] charArray = src.toCharArray();
        for (final char c : charArray) {
            final int index = anagram.indexOf(c);
            if (index == -1) {
                return false;
            }

            anagram = anagram.substring(0, index) + anagram.substring(index + 1, anagram.length());
        }

        return anagram.isEmpty();

    }

}
