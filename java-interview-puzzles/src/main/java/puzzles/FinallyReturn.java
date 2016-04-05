package puzzles;

public class FinallyReturn {

    public static void main(String[] args) {
        System.out.println(decision());

    }

    public static boolean decision() {

        try {
            return true;
        } finally {

            if (true) {
                return false;
            }

        }

    }

}
