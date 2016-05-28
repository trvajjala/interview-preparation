package com.tvajjala.theories;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class SimpleTheory {

    // @DataPoint
    // public static String name = "Thiru";
    //
    // @DataPoint
    // public static String designation = "Developer";
    @DataPoints
    public static String[] data = new String[] { "one" };

    @Theory
    public void sanityCheck(String name, String de) {
        System.out.println("  " + name + " " + de);
    }
}
