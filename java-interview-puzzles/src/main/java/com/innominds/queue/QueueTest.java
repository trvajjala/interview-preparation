package com.innominds.queue;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class QueueTest {

    public static void main(String[] args) {

        final Queue<String> queue = new LinkedList<String>();

        try {
            queue.element();
            queue.remove();
        } catch (final NoSuchElementException e) {

        }

        System.out.println(queue.offer("One"));
        System.out.println(queue.offer("Two"));
        System.out.println(queue.element());// return or throw
        System.out.println(queue.peek());// return or null

        System.out.println(queue.remove()); // delete or throw
        System.out.println(queue.poll());// delete or null

    }
}
