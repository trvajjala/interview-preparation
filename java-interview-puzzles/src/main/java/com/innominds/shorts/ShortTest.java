package com.innominds.shorts;

import java.util.HashSet;
import java.util.Set;

/**
 * i-1 gives an integer which is not equivalent to short value so it won't remove
 * 
 * @author ThirupathiReddy V
 *
 */
public class ShortTest {

    public static void main(String[] args) {
        final Set<Short> set = new HashSet<Short>();

        for (short i = 0; i < 10; i++) {
            set.add(i);
            final boolean flag = set.remove(i - 1);
            System.out.println(flag);
        }

        System.out.println(set.size());

    }

}
