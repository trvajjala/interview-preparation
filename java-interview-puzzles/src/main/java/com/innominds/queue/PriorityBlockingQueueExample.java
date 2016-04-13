package com.innominds.queue;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * put and take method generally used to in blockqueues
 * 
 * @author ThirupathiReddy V
 *
 */
public class PriorityBlockingQueueExample {

    public static void main(String[] args) throws Exception {

        final PriorityBlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue<Integer>();

        final Thread t = new Thread(() -> {
            while (true) {
                blockingQueue.put(new Random().nextInt());

                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (final Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }   );
        t.start();

        while (true) {
            System.out.println(blockingQueue.take());
        }

    }

}
