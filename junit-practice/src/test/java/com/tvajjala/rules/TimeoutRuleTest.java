package com.tvajjala.rules;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;



public class TimeoutRuleTest {

    /**
     * rule must be public
     */
    @Rule
    public Timeout globalTimeOut = new Timeout(40, TimeUnit.MILLISECONDS);

    @Test
    public void simpleTest() throws InterruptedException {
	Thread.sleep(30);
	Assert.assertEquals(true, true);
    }

}
