package com.tvajjala.theories;

import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ExternalTheoryTest {

    @Theory
    public void adds_numbers(@ParametersSuppliedBy(NumberSupplier.class) Number num1, @ParametersSuppliedBy(NumberSupplier.class) Number num2) {
        System.out.println(num1 + " and " + num2);
    }
}
