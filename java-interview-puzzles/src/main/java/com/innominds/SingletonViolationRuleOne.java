package com.innominds;

import java.lang.reflect.Constructor;

public class SingletonViolationRuleOne {

    public static void main(String[] args) throws Exception {

        final Class<?> clazz = Class.forName("com.innominds.Singleton");

        final Constructor<?> con = clazz.getDeclaredConstructor();
        con.setAccessible(true);

        final Singleton instance = (Singleton) con.newInstance();

        System.out.println(instance);
        instance.sayHello();

        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.getInstance());

    }

}

class Singleton {

    private static Singleton INSTANCE;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;

    }

    public void sayHello() {
        System.out.println("Hello world ");
    }
}
