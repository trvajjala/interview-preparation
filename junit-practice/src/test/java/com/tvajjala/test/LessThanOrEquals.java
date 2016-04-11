package com.tvajjala.test;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

//mastering-unit-testing
public class LessThanOrEquals<T extends Comparable<T>> extends BaseMatcher<Comparable<T>> {

    private final Comparable<T> expectedValue;

    public LessThanOrEquals(T expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean matches(Object t) {
        final int compareTo = expectedValue.compareTo((T) t);
        return compareTo > -1;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Less than or equals " + expectedValue);

    }

    @Override
    public void describeMismatch(Object item, Description description) {
        super.describeMismatch(item, description);
    }

    /**
     * The @Factory annotation isn't necessary but needed for a Hamcrest tool. When we create many custom matchers, then it becomes annoying to import them all
     * individually. Hamcrest ships with a org.hamcrest.generator.config.XmlConfigurator command-line tool that picks up predicates annotated with the @Factory
     * annotation and collects them in a Matcher class for easy importing.
     *
     * @param t
     * @return
     */
    @Factory
    public static <T extends Comparable<T>> Matcher<T> lessThanOrEqual(T t) {
        return new LessThanOrEquals(t);
    }

}
