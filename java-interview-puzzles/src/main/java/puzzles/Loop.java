package puzzles;

public class Loop {
    public static void main(String[] args) {
        final int[][] tests = { { 6, 5, 4, 3, 2, 1 }, { 1, 2 }, { 1, 2, 3 }, { 1, 2, 3, 4 }, { 1 } };
        int successCount = 0;

        try {
            int i = 0;
            while (true) {
                if (thirdElementIsThree(tests[i++])) {
                    successCount++;
                }
            }
        } catch (final ArrayIndexOutOfBoundsException e) {
            // No more tests to process
        }
        System.out.println(successCount);
    }

    private static boolean thirdElementIsThree(int[] a) {
        return a.length >= 3 & a[2] == 3;
    }
}
