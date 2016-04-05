package puzzles;

/**
 * When System.exit is called, the virtual machine performs two cleanup tasks before shutting down.<br>
 * First, it executes all shutdown hooks that have been registered with Runtime.addShutdownHook. <br>
 * This is useful to release resources external to the VM.<br>
 * Use shutdown hooks for behavior that must occur before the VM exits.
 *
 * Never call System.runFinalizersOnExit or Runtime.runFinalizersOnExit for any reason: They are among the most dangerous methods in the Java libraries
 *
 * @author ThirupathiReddy V
 *
 */
public class ShutdownHook {

    public static void main(String[] args) {

        try {
            System.out.println("Hello");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    System.out.println(" Bye! ");
                }
            });
            System.exit(2);
            // Runtime.runFinalizersOnExit(true);
        } finally {
            System.out.println("World");
        }
    }
}
