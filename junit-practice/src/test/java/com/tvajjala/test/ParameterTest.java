package com.tvajjala.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.tvajjala.Factorial;

@RunWith(Parameterized.class)
public class ParameterTest {

    @Parameter(value = 0)
    public int input;
    @Parameter(value = 1)
    public int output;

    // public ParameterTest(int input, int output) {
    // this.input = input;
    // this.output = output;
    // }

    @Parameters
    public static Collection<Object[]> factorialData() {

        return Arrays.asList(new Object[][] { { 0, 1 }, { 1, 1 }, { 2, 2 }, { 3, 6 }, { 4, 24 } });
    }

    @Test
    public void factTest() {
        final Factorial factorial = new Factorial();
        System.out.println("factTest test case running with input "+input);
        Assert.assertEquals(output, factorial.factorial(input));

    }

}
