package com.tvajjala.category;

import static com.tvajjala.test.LessThanOrEquals.lessThanOrEqual;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class HamcrestTest {

    @Test
    public void customMatcher() {

	Assert.assertThat(10, lessThanOrEqual(2));

    }

    @Test
    @Category(GoodTestCategory.class)
    public void allOfTest() {
	Assert.assertThat("Some", anything());

    }

    @Test
    public void thatTest() {
	Assert.assertThat(99.9, either(is(100.0)).or(is(99.9)));
	Assert.assertThat(99.0, anyOf(is(99.0), is(2929.00)));
    }

    @Test
    public void collectionMatcherTest() {
	final List<Double> list = Arrays.asList(200.0, 222.0, 88.00, 12.59);
	Assert.assertThat(list, hasItem(200.0));
	Assert.assertThat(list, hasItems(12.59, 88.00));
    }

    @Test
    public void stringMatcherTest() {
	Assert.assertThat("Hello world", containsString("world"));
	Assert.assertThat("Hello world", endsWith("world"));
	Assert.assertThat("Hello world", startsWith("Hello"));
    }

}
