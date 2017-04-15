package com.tvajjala.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class DemoAnnotation {

    public static void main(String[] args) {

        final Class<AnnoExample> anno = AnnoExample.class;

        for (final Method method : anno.getMethods()) {


            for (final Annotation annotation : method.getAnnotations()) {

                if (annotation instanceof Comment) {

                    final Comment comment = (Comment) annotation;
                    System.out.println("Author:  " + comment.author());
                    System.out.println("Description :  " + comment.description());
                    System.out.println("Priority :  " + comment.priority());
                }
            }
        }

    }
}
