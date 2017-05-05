package com.tvajjala.suite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * used to run multiple test classes in a single file
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ SimpleTest.class, AssertDoubleDeprecation.class })
public class RunwithSuiteExample {

    @Test
    public void simpleAliasRunner() {
	System.out.println("Simple Alias Runner ");
    }
}
