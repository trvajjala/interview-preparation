package cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("Reached all the barriers ");
        });

        final Thread t1 = new Thread(new Task(cyclicBarrier), "Thread-1");

        final Thread t2 = new Thread(new Task(cyclicBarrier), "Thread-2");

        final Thread t3 = new Thread(new Task(cyclicBarrier), "Thread-3");

        t1.start();
        t2.start();
        t3.start();

    }
}
