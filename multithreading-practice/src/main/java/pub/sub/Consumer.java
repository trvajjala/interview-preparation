package pub.sub;

import java.util.concurrent.TimeUnit;

public class Consumer extends Thread {

    EventStorage eventStorage;

    public Consumer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            eventStorage.borrowEvent();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
