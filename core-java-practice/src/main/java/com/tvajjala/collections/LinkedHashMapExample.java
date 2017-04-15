package com.tvajjala.collections;

import java.util.LinkedHashMap;

public class LinkedHashMapExample {

    public static void main(String[] args) {

        final LinkedHashMap<Integer, Integer> lMap = new LinkedHashMap<Integer, Integer>();

        lMap.put(1, 1);
        lMap.put(null, 2);
        lMap.put(2, 2);
        lMap.remove(null, 3);
        System.out.println(lMap);

        lMap.remove(null, 2);

        System.out.println(lMap);
    }
}
