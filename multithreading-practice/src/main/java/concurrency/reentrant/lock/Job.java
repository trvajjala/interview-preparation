package concurrency.reentrant.lock;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class Job implements Runnable {

    PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " printing...");
        printQueue.printJob(new Object());
        System.out.println("Thread " + Thread.currentThread().getName() + " printed.");
    }
}
