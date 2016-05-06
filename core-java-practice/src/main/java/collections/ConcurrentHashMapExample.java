package collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;

/**
 * – ConcurrentHashMap is similar to Hashtable, but performs better in a multi-threaded environment as it does not block itself to be access by a single thread
 *
 * – It does not allow duplicate keys.
 *
 * – It does not allow null to be used as a key or value.
 *
 * – Iterators of ConcurrentHashMap don’t throw a ConcurrentModificationException, so we don’t need to lock the collection while iterating it.
 *
 * @author ThirupathiReddy V
 *
 */
public class ConcurrentHashMapExample {

    public static void main(String[] args) {

        final ConcurrentMap<Integer, String> cMap = new ConcurrentHashMap<Integer, String>();

        cMap.put(100, "Hundred");
        cMap.put(200, "TwoHundred");
        cMap.put(300, "ThreeHundred");
        // cMap.put(null, "Null");
        System.out.println(cMap);
        cMap.replace(102, "OneNOTTwo");
        System.out.println(cMap);

        cMap.forEach((K, V) -> {
            System.out.println(K + " = " + V);
        });

        final Consumer<String> con = ConcurrentHashMapExample::some;
        con.accept("SSS");

        try {
            Thread.currentThread().join();
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void some(String hello) {
        System.out.println(hello);
    }

    static boolean isTrue(String name) {

        return true;
    }

}
