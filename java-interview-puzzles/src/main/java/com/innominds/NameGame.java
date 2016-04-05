package com.innominds;

import java.util.IdentityHashMap;
import java.util.Map;

public class NameGame {

    public static void main(String[] args) {

        final Map<String, String> map = new IdentityHashMap<String, String>();

        map.put("Micky", "Mouse");
        map.put("Micky", "Mantle");

        System.out.println(map.size());
    }
}
