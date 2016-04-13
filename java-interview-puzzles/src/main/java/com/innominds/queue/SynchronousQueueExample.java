package com.innominds.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * 1) SynchronousQueue blocks until another thread is ready to take the element, one thread is trying to put.
 * 
 * 2) SynchronousQueue has zero capacity.
 * 
 * 3) SynchronousQueue is used to implement queuing strategy of direct hand-off, where thread hands-off to waiting thread, else creates new one if allowed, else
 * task rejected.
 * 
 * 4) This queue does not permit null elements, adding null elements will result in NullPointerException.
 * 
 * 5) For purposes of other Collection methods (for example contains), a SynchronousQueue acts as an empty collection.
 * 
 * 6) You cannot peek at a synchronous queue because an element is only present when you try to remove it; similarly you cannot insert an element (using any
 * method) unless another thread is trying to remove it.
 * 
 * 7) You cannot iterate over SynchronousQueue, as there is nothing to iterate
 * 
 * 8) A SynchronousQueue constructed with fairness policy set to true grants threads access in FIFO order.
 * 
 * 
 * @author ThirupathiReddy V
 *
 */
public class SynchronousQueueExample {

    public static void main(String[] args) {

        final SynchronousQueue<String> sq = new SynchronousQueue<String>();

        final Thread produces = new Thread(() -> {
            try {
                sq.put("EO");
            } catch (final Exception e) {
            }
        });

        produces.start();

        final Thread consumer = new Thread(() -> {
            System.out.println(sq.peek());
        });
        consumer.start();

    }
}
