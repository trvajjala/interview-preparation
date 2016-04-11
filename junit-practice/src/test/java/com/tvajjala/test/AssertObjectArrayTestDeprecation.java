package com.tvajjala.test;

import org.junit.Assert;
import org.junit.Test;

public class AssertObjectArrayTestDeprecation {

    @Test
    public void test() {
        Assert.assertEquals(new Object[] { 4, 5 }, new Object[] { 4, 5 });
    }
}
