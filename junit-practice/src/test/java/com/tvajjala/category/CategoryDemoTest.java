package com.tvajjala.category;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(GoodTestCategory.class)
@SuiteClasses(HamcrestTest.class)
public class CategoryDemoTest {





}

interface GoodTestCategory{

}
