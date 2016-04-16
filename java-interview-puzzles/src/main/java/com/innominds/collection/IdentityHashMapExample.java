package com.innominds.collection;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapExample {
    public static void main(String[] args) {
        final Map<Integer, Integer> iMap = new IdentityHashMap<>();

        iMap.put(new Integer(1), 1);
        iMap.put(1, 1);
        iMap.put(1, 1);
        System.out.println(iMap.size());
    }
}
