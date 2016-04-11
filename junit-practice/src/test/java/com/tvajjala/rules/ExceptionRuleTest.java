package com.tvajjala.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionRuleTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void illegalException() {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Some Text");
        throw new IllegalStateException("Some Text");
    }
}
