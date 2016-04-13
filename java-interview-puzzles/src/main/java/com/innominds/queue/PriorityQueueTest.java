package com.innominds.queue;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * priority queue violates the FIFO principle use LinkedList instead of PriorityQueue if you want to follow FIFO
 *
 * PriorityQueue is similar to TreeMap or TreeSet which sorts the elements
 *
 * @author ThirupathiReddy V
 *
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        // final Queue<String> queue = new LinkedList<String>();
        final Queue<String> queue = new PriorityQueue<String>();
        queue.poll();
        queue.add("1");
        queue.offer("3");
        queue.offer("2");

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.poll());

    }
}
