package wildcard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnboundedWildCardExample {

    public static void main(String[] args) {

        final List<String> nameList = new ArrayList<String>();
        nameList.add("One");
        nameList.add("Two");
        nameList.add("Three");
        add(nameList, "Two");
    }

    public static void add(List<? super String> list, String element) {

        final Iterator<?> it = list.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

}
