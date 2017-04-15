package com.tvajjala.annotation;

import com.tvajjala.annotation.Comment.Priority;

@ClassInfo(author = "Thiru", createdOn = "25 Mar 2016", version = 1)
public class AnnoExample {

    @Comment(author = "ThirupathiReddy", description = "this is used to annotation testing", priority = Priority.HIGH)
    public void doSth() {
        System.out.println(" Hello world ");

    }
}
