package pattern.creational.objectpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduletest {

    public static void main(String[] args) {

        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

        executorService.scheduleAtFixedRate(() -> {
            System.out.println(" running ");
        }, 1, 1, TimeUnit.SECONDS);

    }
}
