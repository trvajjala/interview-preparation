package concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo {

    public static void main(String[] args) {

	final ExecutorService executorService = Executors.newSingleThreadExecutor();

	executorService.submit(()->{  try {
	    Thread.sleep(1000);
	    System.out.println("Done 1");
	} catch (final InterruptedException e) {
	    e.printStackTrace();
	}});

	executorService.submit(()->{  try {
	    Thread.sleep(1000);
	    System.out.println("Done 2");
	} catch (final InterruptedException e) {
	    e.printStackTrace();
	}});


	executorService.shutdown();

    }

}
