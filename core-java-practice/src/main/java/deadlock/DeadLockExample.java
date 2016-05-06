package deadlock;

/**
 * Run jps -lv ==> gives to Process Id of this class<br>
 *
 * jstack -F PID==> gives thread dump
 *
 * @author ThirupathiReddy V
 *
 */
public class DeadLockExample {

    public static void main(String[] args) {
        new DeadLockExample().createDeadLock();
    }

    Object mutex1 = new Object();
    Object mutex2 = new Object();

    public void createDeadLock() {

        final Thread t1 = new Thread(() -> {

            synchronized (mutex1) {
                try {
                    Thread.sleep(1000);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                synchronized (mutex2) {
                    try {
                        Thread.sleep(1000);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "BhanuThread");

        final Thread t2 = new Thread(() -> {

            synchronized (mutex2) {
                try {
                    Thread.sleep(1000);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
                synchronized (mutex1) {
                    try {
                        Thread.sleep(1000);
                    } catch (final Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "KarthiThread");

        t1.start();
        t2.start();

    }
}
