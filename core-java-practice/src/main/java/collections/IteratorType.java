package collections;

import java.util.ArrayList;
import java.util.HashSet;

public class IteratorType {

    public static void main(String[] args) {

        final ArrayList<String> list = new ArrayList<String>();

        System.out.println(list.iterator());

        final HashSet<String> set = new HashSet<String>();
        System.out.println(set.iterator());
    }
}
