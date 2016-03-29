package reentrant.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class PrintQueue {

    private final Lock lock = new ReentrantLock(true);

    // fair mode will gives control to the most awaiting thread

    public void printJob(Object document) {

        lock.lock();
        long duration = (long) (Math.random() * 5000);
        try {
            System.out.println(" printing-1 " + Thread.currentThread().getName());
            Thread.sleep(duration);
        } catch (final InterruptedException e) {
        } finally {
            lock.unlock();
        }

        lock.lock();

        duration = (long) (Math.random() * 5000);

        try {
            System.out.println(" printing-2 " + Thread.currentThread().getName());
            Thread.sleep(duration);
        } catch (final InterruptedException e) {
        } finally {
            lock.unlock();
        }

    }

}
