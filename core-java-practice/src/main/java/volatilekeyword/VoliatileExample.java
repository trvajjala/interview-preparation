package volatilekeyword;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class VoliatileExample {
    static int a = 0;

    public static void main(String[] args) throws Exception {

        final Runnable r = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                if (a == 0) {
                    a = 4;
                } else {
                    a = 3;
                }

            } catch (final Exception e) {
                e.printStackTrace();
            }
        };
        final Thread t = new Thread(r);
        final Thread t2 = new Thread(r);
        t.start();
        t2.start();
        t.join();
        t2.join();
        System.out.println(" a = " + a);

    }
}
