package strictfpoint;

/**
 * in normal case calculations are done by the underlying processor. <br>
 * using strictfp that is done by JVM itself by following IEEE standards without using this keyword you may expect difference in result. depending on your
 * hardware. <br>
 * this keyword can applied to class, interface, method as well
 *
 * @author ThirupathiReddy V
 *
 */
public class StrictFloatingPoints {

    public static void main(String[] args) {

        final double num1 = 10e+102;
        final double num2 = 6e+08;

        calculate(num1, num2);

        calculateStrict(num1, num2);

    }

    static strictfp void calculateStrict(double d1, double d2) {

        System.out.println(d1 + d2);
    }

    static void calculate(double d1, double d2) {

        System.out.println(d1 + d2);
    }
}
