package primegen;
import java.math.BigInteger;
import java.util.Random;

public class ProbablePrimeDemo {

    public static void main(String[] args) {

        final BigInteger bigInteger = BigInteger.probablePrime(4, new Random());
        System.out.println(bigInteger.nextProbablePrime());

    }
}
