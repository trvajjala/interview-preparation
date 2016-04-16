package com.innominds.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierExample {

    public static void main(String[] args) {

        final List<Integer> list = Arrays.asList(3, 23, 0, 22);
        list.forEach(x -> {
            print(() -> x);
        });
    }

    static void print(Supplier<Integer> supplier) {

        System.out.println(supplier.get());
    }
}
