package collections;

import java.util.TreeMap;

public class TreeMapExample {

    public static void main(String[] args) {

        final TreeMap<Integer, String> tMap = new TreeMap<Integer, String>((o1, o2) -> o2.compareTo(o1));

        tMap.put(1, "1");
        tMap.put(2, "2");
        tMap.put(3, "3");
        // tMap.put(null, "");

        System.out.println(tMap);
    }
}
