package concurrency.join;

/**
 * It suspends the calling thread execution
 *
 * @author ThirupathiReddy V
 *
 */
public class JoinExample {

    public static void main(String[] args) throws Exception {

        final DatasourceThread t1 = new DatasourceThread();
        final NetworkThread t2 = new NetworkThread();

        System.out.println("Exuecting sth");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println("Executing finalization");
    }

}

class DatasourceThread extends Thread {

    @Override
    public void run() {

        try {
            System.out.println("Database operations started....");
            Thread.sleep(4000);
            System.out.println("Database operations completed.");
        } catch (final Exception e) {

        }
    }
}

class NetworkThread extends Thread {

    @Override
    public void run() {

        try {
            System.out.println("Network operations started .....");
            Thread.sleep(3000);
            System.out.println("Network operations completed.");
        } catch (final Exception e) {

        }
    }
}