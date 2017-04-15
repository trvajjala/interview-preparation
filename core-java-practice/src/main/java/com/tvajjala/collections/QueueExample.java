package com.tvajjala.collections;

import java.util.PriorityQueue;
import java.util.Queue;

public class QueueExample {

    public static void main(String[] str) {

	final Queue<Integer> queue = new PriorityQueue<Integer>(3);
	queue.offer(10);
	queue.offer(333);
	queue.offer(22);
	queue.offer(12);
	queue.offer(2);
	queue.add(233);

	System.out.println(queue);
	final Integer element = queue.poll();
	System.out.println("Element " + element + " size is " + queue.size());

	final Integer ele = queue.peek();
	System.out.println("Element " + ele + " size is " + queue.size());

	System.out.println(queue);

    }
}
