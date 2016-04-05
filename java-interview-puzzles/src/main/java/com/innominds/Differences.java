package com.innominds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Differences {

    public static void main(String[] args) {
        final int[] vals = { 789, 678, 567, 456, 345, 234, 123, 012 };
        final Set<Integer> diffs = new HashSet<Integer>();

        for (int i = 0; i < vals.length; i++) {
            for (int j = i; j < vals.length; j++) {
                diffs.add(vals[i] - vals[j]);
            }
        }
        System.err.println(diffs);
        System.out.println(diffs.size());
    }

    static <E> List<E> getList(List<E> list) {

        return new ArrayList<E>(new LinkedHashSet<E>(list));

    }
}
