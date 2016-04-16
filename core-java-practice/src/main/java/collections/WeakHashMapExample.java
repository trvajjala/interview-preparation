package collections;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapExample {

    public static void main(String[] args) {
        final Map<Integer, Integer> wMap = new WeakHashMap<Integer, Integer>();
        for (int i = 0; i < 1000000; i++) {
            wMap.put(i, 1);
        }
        System.out.println(wMap.size());
        System.gc();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutdown hook executed...");
        }));

        System.out.println(wMap.size());
    }
}
