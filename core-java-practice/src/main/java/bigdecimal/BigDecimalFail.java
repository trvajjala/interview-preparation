package bigdecimal;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * is this bug ?
 * 
 * @author ThirupathiReddy V
 *
 */
public class BigDecimalFail {

    public static void main(String[] args) {

        final BigDecimal bigDecimal = new BigDecimal("1.00");

        final BigDecimal bigDecimal2 = new BigDecimal("1.0");

        final Set<BigDecimal> set = new HashSet<BigDecimal>();
        set.add(bigDecimal2);
        set.add(bigDecimal);

        System.out.println(set.size());

        final Set<BigDecimal> list = new TreeSet<BigDecimal>();
        list.add(bigDecimal2);
        list.add(bigDecimal);

        System.out.println(list.size());

    }
}
