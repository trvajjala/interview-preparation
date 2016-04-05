package com.innominds;

import java.util.HashSet;
import java.util.Set;

public class Name {

    String first;
    String last;

    Name(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public boolean equals(Object obj) {

        return ((Name) obj).first.equals(first) && ((Name) obj).last.equals(last);
    }

    @Override
    public int hashCode() {
        return 37 * first.length() + last.length();
    }

    public static void main(String[] args) {

        final Set<Name> set = new HashSet<Name>();

        set.add(new Name("thiru", "vajjala"));

        System.out.println(set.contains(new Name("thiru", "vajjala")));

    }

}
