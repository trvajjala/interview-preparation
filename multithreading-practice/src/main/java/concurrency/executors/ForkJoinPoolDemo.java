package concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This works for divide-and-conquer algorithms <br>
 *
 * The ForkJoinPool is similar to the Java ExecutorService but with one
 * difference.<br>
 * The ForkJoinPool makes it easy for tasks to split their work up into smaller
 * tasks which are then submitted to the ForkJoinPool too. Tasks can keep
 * splitting their work into smaller subtasks for as long as it makes to split
 * up the task.
 *
 *
 * @author ThirupathiReddy V
 *
 */
public class ForkJoinPoolDemo {

    public static void main(String[] args) {

	final ExecutorService service = Executors.newWorkStealingPool();

	service.submit(() -> {
	    System.out.println("Done");
	});

	service.shutdown();

    }
}
