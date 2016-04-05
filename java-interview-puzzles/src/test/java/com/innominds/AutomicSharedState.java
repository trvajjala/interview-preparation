package com.innominds;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class AutomicSharedState {
    @Test
    public void atomicSharedState() {
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final AtomicCounter c = new AtomicCounter();
        executorService.execute(new CounterSetter(c));
        final int value = c.getNumber().incrementAndGet();
        assertEquals(1, value);
    }

    private static class CounterSetter implements Runnable {
        private final AtomicCounter counter;

        private CounterSetter(AtomicCounter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            while (true) {
                counter.getNumber().set(0);
            }
        }
    }

    public static class AtomicCounter {

        private final AtomicInteger number = new AtomicInteger(0);

        public AtomicInteger getNumber() {
            return number;
        }
    }
}
