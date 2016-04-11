package com.tvajjala.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class SimpleTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass");
    }

    @Before
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @Test
    @Ignore("This is ignore case  scenario")
    public void testOne() {

        System.out.println("executing testOne");
        Assert.assertEquals('c', 'c');
    }

    @Test
    public void testTwo() {

        System.out.println("executing testTwo");
        Assert.assertEquals('3', '3');
    }

    @After
    public void afterTest() {
        System.out.println("afterTest");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass");
    }
}
