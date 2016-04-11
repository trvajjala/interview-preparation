package com.tvajjala.test;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

public class AssumeTest {

    @Test
    public void simpleAssumptionTest() {
        Assume.assumeFalse(true);
        Assert.assertTrue(true);
    }
}
