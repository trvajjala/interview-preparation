package thread.local;

public class ThreadLocalExample {

    public static void main(String[] args) {
        final ThreadLocal<Integer> tl = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 100;
            }
        };
        final Th t = new Th(tl);
        t.start();

    }

}

class Th extends Thread {
    ThreadLocal<Integer> tl;

    public Th(ThreadLocal<Integer> tl) {
        this.tl = tl;
    }

    @Override
    public void run() {
        System.out.println(tl.get());
    }
}
