package com.tvajjala.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * used to fix execution order <br>
 * NAME_ASCENDING <br>
 * JVM <br>
 * DEFAULT
 *
 * @author ThirupathiReddy V
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOrderingTest {

    @Test
    public void aTest() {

        System.out.println("aTest");
    }

    @Test
    public void bTest() {
        System.out.println("bTest");
    }

    @Test
    public void cTest() {
        System.out.println("cTest");
    }
}
