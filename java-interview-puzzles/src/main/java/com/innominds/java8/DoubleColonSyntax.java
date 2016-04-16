package com.innominds.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DoubleColonSyntax {

    public static void main(String[] args) {

        final Runnable r1 = () -> {
            System.out.println("Lambda expression executed.");
        };

        r1.run();

        final Runnable r2 = DoubleColonSyntax::print;
        r2.run();

        final Consumer<String> c1 = DoubleColonSyntax::printOneArg;

        c1.accept("ThirupathiReddy ");

        final List<Integer> list = Arrays.asList(39, 1, 23, 4, 56, 7, 9, 99, 29);
        eval(list, n -> n % 2 == 00);
    }

    static void print() {
        System.out.println("Printing something with double colon check.");
    }

    static void printOneArg(String name) {
        System.out.println("Printing one argment " + name);
    }

    static void eval(List<Integer> list, Predicate<Integer> predicate) {

        for (final Integer i : list) {

            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }
}
