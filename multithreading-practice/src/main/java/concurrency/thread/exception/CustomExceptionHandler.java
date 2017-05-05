package concurrency.thread.exception;

import java.lang.Thread.UncaughtExceptionHandler;

public class CustomExceptionHandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {

	System.out.println(" Thread " + t);
	System.out.println("Throwable " + e);
    }
}
