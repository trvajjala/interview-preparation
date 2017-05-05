package concurrency.theadlocal;

/**
 * The ThreadLocal class in Java enables you to create variables that can only
 * be read and written by the same thread. Thus, even if two threads are
 * executing the same code, and the code has a reference to a ThreadLocal
 * variable, then the two threads cannot see each other's ThreadLocal variables.
 *
 * @author ThirupathiReddy V
 *
 */
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
