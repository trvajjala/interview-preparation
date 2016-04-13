package com.innominds.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockQueueExample {

    public static void main(String[] args) throws Exception {
        final ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(4);

        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (final Exception e) {
            }

            while (true) {
                try {
                    System.out.println("Removing element :  -> " + abq.take());

                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (final Exception e) {
                    }

                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();

        while (true) {

            System.out.println("Adding element ");

            for (int i = 0; i < 5; i++) {
                abq.put(i);
                abq.put(i);
            }

        }

    }
}
