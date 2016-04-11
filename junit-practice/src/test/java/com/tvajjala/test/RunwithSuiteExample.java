package com.tvajjala.test;

import org.junit.Ignore;
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
    @Ignore("This won't execute as it is suite ")
    public void simpleAliasRunner() {
        System.out.println("Simple Alias Runner ");
    }
}
