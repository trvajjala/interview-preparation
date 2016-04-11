package com.tvajjala.rules;

import java.util.Arrays;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ErrorCollectorTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    /**
     * useful to check multiple conditions on single testCase
     */
    @Test
    public void failAfterExecution() {
        collector.checkThat("a", CoreMatchers.equalTo("b"));
        collector.checkThat(1, CoreMatchers.equalTo(2));
        collector.checkThat(Arrays.asList(3, 4), CoreMatchers.hasItem(2));

    }
}
