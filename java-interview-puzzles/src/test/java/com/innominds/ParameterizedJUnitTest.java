package com.innominds;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * When a JUnit test is annotated with @RunWith(Parameterized.class), several changes are made<br>
 * to the life cycle of the test and the way the test is run. A class-level method providing the<br>
 * test data is expected, and this returns an array of data to use for testing.<br>
 * This data could be hard-coded in the test, or for more sophisticated tests, this could be<br>
 * dynamically produced, or even pulled in from a file system, database, or another relevant<br>
 * storage mechanism.
 *
 * However this data is generated, each element in the array from this method is passed into the <br>
 * constructor for the test suite, and all tests run with that data.
 *
 * @author ThirupathiReddy V
 *
 */
@RunWith(Parameterized.class)
public class ParameterizedJUnitTest {

    private final int a;
    private final int b;
    private final int expectedAddition;
    private final int expectedSubtraction;
    private final int expectedMultiplication;
    private final int expectedDivision;

    public ParameterizedJUnitTest(final int a, final int b, final int expectedAddition, final int expectedSubtraction, final int expectedMultiplication,
            final int expectedDivision) {
        this.a = a;
        this.b = b;
        this.expectedAddition = expectedAddition;
        this.expectedSubtraction = expectedSubtraction;
        this.expectedMultiplication = expectedMultiplication;
        this.expectedDivision = expectedDivision;
    }

    @Parameterized.Parameters
    public static List<Integer[]> parameters() {
        return new ArrayList<Integer[]>(3) {
            /**
         *
         */
         private static final long serialVersionUID = 3563582775066332146L;

            {
                add(new Integer[] { 1, 2, 3, -1, 2, 0 });
                add(new Integer[] { 0, 1, 1, -1, 0, 0 });
                add(new Integer[] { -11, 2, -9, -13, -22, -5 });
            }
        };
    }

    @Test
    public void addNumbers() {
        assertEquals(expectedAddition, a + b);
    }

    @Test
    public void subtractNumbers() {
        assertEquals(expectedSubtraction, a - b);
    }

    @Test
    public void multiplyNumbers() {
        assertEquals(expectedMultiplication, a * b);
    }

    @Test
    public void divideNumbers() {
        assertEquals(expectedDivision, a / b);
    }

}
