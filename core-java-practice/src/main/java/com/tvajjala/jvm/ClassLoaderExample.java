package com.tvajjala.jvm;
public class ClassLoaderExample {

    public static void main(String[] args) {

        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoaderExample.class.getClassLoader());
        System.out.println(ClassLoaderExample.class.getClassLoader().getParent());
        System.out.println(ClassLoaderExample.class.getClassLoader().getParent().getParent());
    }
}
