package concurrency.executors;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RecursiveAction;

/**
 * Useful to simplify producer and consumer problem
 *
 * @author ThirupathiReddy V
 *
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

	final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

	new Thread(new Producer(queue)).start();

	new Thread(new Consumer(queue)).start();



    }

}

class Action extends RecursiveAction{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void compute() {

	//explore more about RecursiveAction use  RecursiveTask
    }
}

class Producer implements Runnable {

    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
	this.queue = queue;
    }

    @Override
    public void run() {
	while (true) {
	    queue.offer(new Date().toString());
	    try {
		Thread.sleep(1000);
	    } catch (final InterruptedException e) {
		Thread.currentThread().interrupt();//is this best practice?
	    }
	}
    }

}

class Consumer implements Runnable {

    private final BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
	this.queue = queue;
    }

    @Override
    public void run() {
	while (true) {
	    try {
		System.out.println(queue.take());
	    } catch (final InterruptedException e1) {
		e1.printStackTrace();
	    }

	}
    }

}