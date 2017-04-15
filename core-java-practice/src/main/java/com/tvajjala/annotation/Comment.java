package com.tvajjala.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Comment {

    enum Priority {
        LOW, MEDIUM, HIGH
    };

    String author() default "";

    String description() default "";

    Priority priority() default Priority.LOW;

}
