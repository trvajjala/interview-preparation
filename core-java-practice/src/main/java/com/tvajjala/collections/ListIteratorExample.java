package com.tvajjala.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIteratorExample {

    public static void main(String[] args) {

        final List<Integer> list = new ArrayList<Integer>();

        list.add(11);
        list.add(110);
        list.add(1022);
        list.add(44);
        list.add(333);

        final Iterator<Integer> iterator = list.listIterator(2);

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
