package com.innominds.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This will follow FIFO principle and blocks while reading only not while inserting.<br>
 * If you want data to be flow FIFO then LinkedList or LinkedBlockingQueue are two options.<br>
 * Pass capacity if u want to restrict the elements in the queue.
 *
 * @author ThirupathiReddy V
 *
 */
public class LinkedBlockingQueueExample {

    public static void main(String[] args) throws Exception {

        final LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<Integer>();// unbounded if it default constructor

        new Thread(() -> {

            while (true) {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Adding " + i);
                        lbq.put(i);
                    }

                    TimeUnit.SECONDS.sleep(4);
                } catch (final Exception e) {
                    e.printStackTrace();
                }

            }

        }).start();

        while (true) {
            final Integer i = lbq.take();
            System.out.println("Taking element " + i);
        }

    }
}
