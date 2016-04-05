package puzzles;

public class PingPong {

    public static void main(String[] args) {

        final Thread t = new Thread(() -> {
            System.out.println(pong());
        });
        t.run();

        System.out.println("Ping");
    }

    static synchronized String pong() {

        return "pong";
    }
}
