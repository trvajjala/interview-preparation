package concurrency.reentrant.lock;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class LockExample {

    public static void main(String[] args) {

        final Thread[] tList = new Thread[10];

        final PrintQueue printQueue = new PrintQueue();
        for (int i = 0; i < 10; i++) {
            tList[i] = new Thread(new Job(printQueue), "PrintJob" + i);
        }

        for (final Thread t : tList) {
            t.start();
        }

    }
}
