package com.innominds.files;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HashCodeToString {

    public static void main(String[] args) {

        final int[] a = new int[] { 3, 32, 1, 1991, 100 };

        System.out.println(Arrays.hashCode(a.clone()));
        // Arrays.fill(a, 402); // replaces all the elements with given element
        System.out.println(Arrays.toString(a));

        System.out.println(a.hashCode());// non-compliant
        System.out.println(a.toString());// non-compliant
    }

    void myCondition() throws InterruptedException {

        final Lock lock = new ReentrantLock(true);

        final Condition condition = lock.newCondition();
        condition.await();

        lock.unlock();

    }

}
