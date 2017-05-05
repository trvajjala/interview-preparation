package concurrency.deamons;

public class DeamonThread {

    public static void main(String[] args) {
	final Thread thraed = new Thread(() -> {
	    System.out.println("Hello");
	});
	thraed.setDaemon(true);
	thraed.start();
    }
}
