package com.innominds.java8;

import java.util.Arrays;
import java.util.List;

public class InterfaceMethods implements Student, Person {

    public static void main(String[] args) {

        final List<Integer> list = Arrays.asList(3993, 03030, 2929);

        list.forEach(t -> System.out.println(t));

        final Student s1 = (s) -> {
            System.out.println("Your name " + s);
        };

        s1.setName("Hello World");

        Student.age();
        new InterfaceMethods().name();
        new InterfaceMethods().setName("");
    }

    @Override
    public void setName(String name) {
        System.out.println("name " + name);
    }

    @Override
    public void name() {
        Student.super.name();
    }
}

@FunctionalInterface
interface Student {

    static void age() {

    }

    default void name() {
        System.out.println("Student name ");
    }

    void setName(String name);
}

interface Person {

    default void name() {
        System.out.println("Person name");
    }

}
