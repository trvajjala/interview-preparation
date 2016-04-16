package puzzles;

public class Reluctunant {
    Reluctunant reluctunant = new Reluctunant();// recursive invocation

    public Reluctunant() throws Exception {
        // throw new Exception("Errr");
    }

    public static void main(String[] args) {

        try {
            new Reluctunant();
            System.out.println("Surprise");
        } catch (final Exception e) {
            // e.printStackTrace();
            System.out.println("You got error ");
        }

    }
}
