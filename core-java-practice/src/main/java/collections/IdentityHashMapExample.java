package collections;

import java.util.IdentityHashMap;

public class IdentityHashMapExample {

    public static void main(String[] args) {

        final IdentityHashMap<Integer, Integer> iMap = new IdentityHashMap<>();

        iMap.put(new Integer(1), 1);
        iMap.put(1, 1);
        iMap.put(1, 1);
        System.out.println(iMap.size());

    }
}
