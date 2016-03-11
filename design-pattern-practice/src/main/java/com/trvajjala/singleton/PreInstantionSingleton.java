package com.trvajjala.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PreInstantionSingleton implements Serializable, Cloneable {

    private static PreInstantionSingleton INSTANCE = null;

    private PreInstantionSingleton() {
    }

    public static PreInstantionSingleton getInstance() {

        if (INSTANCE == null) {
            // https://youtu.be/GH5_lhFShfU
            synchronized (PreInstantionSingleton.class) {

                if (INSTANCE == null) {
                    INSTANCE = new PreInstantionSingleton();
                }

            }

        }

        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

}

class TestSingleton {

    static void useSingleton() {
        final PreInstantionSingleton p = PreInstantionSingleton.getInstance();
        System.out.println("Hashcode : " + p.hashCode());

    }

    public static void main(String[] args) throws Exception {
        //

        final ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestSingleton::useSingleton);
        service.submit(TestSingleton::useSingleton);
        service.shutdown();

        // Reflection violation
        final Class clazz = Class.forName("com.trvajjala.singleton.PreInstantionSingleton");
        final Constructor<PreInstantionSingleton> con = clazz.getDeclaredConstructor();
        con.setAccessible(true);

        final PreInstantionSingleton p1 = con.newInstance();
        System.out.println(p1);

        final PreInstantionSingleton p2 = PreInstantionSingleton.getInstance();
        System.out.println(p2);

        final PreInstantionSingleton p3 = PreInstantionSingleton.getInstance();
        System.out.println(p3);

        // serialization violation

        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        oos.writeObject(p3);
        oos.close();

        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
        final PreInstantionSingleton p4 = (PreInstantionSingleton) ois.readObject();
        ois.close();
        System.out.println(p4);

        // cloning violation
        final PreInstantionSingleton p5 = (PreInstantionSingleton) p2.clone();

        System.out.println(p5);

    }
}
