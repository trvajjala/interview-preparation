package pub.sub;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {

    private final EventStorage eventStorage;

    public Producer(EventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            eventStorage.addEvent();

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (final InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
