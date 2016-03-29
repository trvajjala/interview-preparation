package exception.handling;

public class ExampleException {

    public static void main(String[] args) {
        final Thread t = new Thread(() -> {
            System.out.println(1 / 0);
        });

        t.setUncaughtExceptionHandler(new CustomExceptionHandler());
        t.start();
    }
}
