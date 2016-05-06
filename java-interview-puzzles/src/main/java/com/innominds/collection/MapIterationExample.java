package com.innominds.collection;

import java.util.LinkedList;

public class MapIterationExample {

    public static void main(String[] args) {

        final LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(5);
        ll.add(9);
        ll.add(9);

        System.out.println(ll.get(ll.size() / 2));

    }
}
