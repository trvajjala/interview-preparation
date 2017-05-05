package com.tvajjala.parameterized;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.tvajjala.Factorial;
/**
 * Parameter test with @Paramter annotation
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(Parameterized.class)
public class ParameterAnnotationest {

    @Parameter(value = 0)
    public int input;

    @Parameter(value = 1)
    public int output;


    @Parameters
    public static Collection<Object[]> factorialData() {
	return Arrays.asList(new Object[][] { { 0, 1 }, { 1, 1 }, { 2, 2 }, { 3, 6 }, { 4, 24 } });
    }

    @Test
    public void factorialTest() {
	final Factorial factorial = new Factorial();
	System.out.println("Factorial testCase running with input "+input);
	Assert.assertEquals(output, factorial.factorial(input));

    }

}
