package com.innominds;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * CountDownLatch to run Asynchronous task in JUnit TestCases
 *
 * @author ThirupathiReddy V
 *
 */
public class ExecutorTest {

    @Test
    public void printAsyncTest() throws InterruptedException {

        final ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        executorService.execute(new InfiniteThread(countDownLatch));
        countDownLatch.await(5, TimeUnit.SECONDS);
        executorService.shutdown();

    }

    public static class InfiniteThread implements Runnable {
        CountDownLatch countDownLatch;

        public InfiniteThread(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                System.out.println(" Printing " + i);
            }
            countDownLatch.countDown();
        }
    }
}
