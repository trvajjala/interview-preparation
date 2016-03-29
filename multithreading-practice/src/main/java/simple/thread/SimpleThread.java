package simple.thread;

public class SimpleThread {

    public static void main(String[] args) {

        final Thread thread = new Thread(() -> {
            System.out.println("Hello");
        });

        thread.start();
    }
}
