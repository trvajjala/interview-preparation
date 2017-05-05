package com.tvajjala.parameterized;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * When u run with parameters it should have one constructor with those values
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(Parameterized.class)
public class ConstructorParamTest {

    int input;
    int second;
    int expected;

    public ConstructorParamTest(int input, int second, int expected) {
	this.input = input;
	this.second = second;
	this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
	return Arrays.asList(new Object[][] { { 2, 4, 6 }, { 3, 9, 12 } });
    }

    @Test
    public void squareTest() {
	Assert.assertEquals(expected, input + second);
    }

}
