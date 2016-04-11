package com.tvajjala.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParamTest {

    int input;
    int ip;
    int expected;

    public ParamTest(int input, int ip, int expected) {
        this.input = input;
        this.ip = ip;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { 2, 4, 6 }, { 3, 9, 12 } });
    }

    @Test
    public void squareTest() {

        Assert.assertEquals(expected, input + ip);
    }

}
