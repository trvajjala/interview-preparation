package com.innominds.streams;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class SimpleStreamExample {

    public static void main(String[] args) {

        final List<Integer> list = Arrays.asList(2, 3, 2, 1, 0, 12, 12323, 222);

        final ListIterator<Integer> li = list.listIterator();
        while (li.hasNext()) {
            System.out.println(li.next());
        }
        while (li.hasPrevious()) {
            System.out.println(li.previous());
        }

        list.sort((c1, c2) -> Integer.compare(c1, c2));
        list.stream().skip(3).forEach(item -> {
            System.out.println(item);
        });

        list.stream().limit(4).skip(0).filter(a -> (a < 1003)).map(item -> (item + 1)).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println(list);

    }
}
