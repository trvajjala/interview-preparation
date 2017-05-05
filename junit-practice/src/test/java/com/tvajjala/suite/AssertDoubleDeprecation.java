package com.tvajjala.suite;

import org.junit.Assert;
import org.junit.Test;

public class AssertDoubleDeprecation {

    /**
     * Sometimes double can lead to surprising results due to the representation<br>
     * that Java uses to store doubles. Any operation on a double value can <br>
     * lead to an unexpected result. <br>
     * Assert doesn't rely on double comparison;<br>
     * so, assertEquals(double expected, double actual) is deprecated
     */

    /**
     * The assert method provides an overloaded method for the double value <br>
     * assertion, that is, assertEquals(double expected, double actual, double delta). During comparison, if the difference between the expected and the actual
     * value is less than the delta value, the result is considered passed.
     */
    @Test
    public void testDouble() {
        Assert.assertEquals(4.099d, 3.099d + 1.0, 0.2);
    }

}
