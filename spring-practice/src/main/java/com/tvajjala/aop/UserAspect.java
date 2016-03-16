package com.tvajjala.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class UserAspect {

    @Pointcut("execution(** com.tvajjala.service.*.getName())")
    public void reusablePointCut() {

    }

    @After("reusablePointCut()")
    public void beforeGetName() {
        System.out.println(" $$$$$$$$$$$$$$$$$$$$$$$$$$$$  ");
    }

    @Around("reusablePointCut()")
    public void aroundAdvice(ProceedingJoinPoint pj) {

        System.out.println("before AroundAdvice");
        try {
            final Object object = pj.proceed();
            System.out.println("object " + object);
            System.out.println("after AroundAdvice ");
        } catch (final Throwable e) {
            e.printStackTrace();
        }

        System.out.println("After");

    }

}
