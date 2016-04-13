package com.innominds.collection;

import java.util.ArrayList;

public class EnsumeCapacity {

    public static void main(String[] args) {

        final ArrayList<Integer> list = new ArrayList<Integer>();

        list.ensureCapacity(4000);

        System.out.println(list.size());
    }
}
