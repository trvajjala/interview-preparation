package com.innominds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Write a method that takes a List of elements and returns a new List containing<br>
 * the same elements in the same order with the second and subsequent occurrences <br>
 * of any duplicate elements removed. For example, if you pass in a list containing "spam",<br>
 * "sausage", "spam", "spam", "bacon", "spam", "tomato", and "spam", <br>
 * you’ll get back a new list containing "spam", "sausage", "bacon", and "tomato".
 *
 * @author ThirupathiReddy V
 *
 */
public class ListOrderPreserve {

    public static void main(String[] args) {

        System.out.println(getList(Arrays.asList("Hello", "world", "Hello", "Hello", "One")));

    }

    static <E> List<E> getList(List<E> list) {

        return new ArrayList<E>(new LinkedHashSet<E>(list));

    }
}
