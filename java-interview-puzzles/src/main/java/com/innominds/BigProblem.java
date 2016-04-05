package com.innominds;

import java.math.BigInteger;

public class BigProblem {

    public static void main(String[] args) {

        final BigInteger fiveThousand = new BigInteger("5000");
        final BigInteger fiftyThousand = new BigInteger("50000");

        final BigInteger total = BigInteger.ZERO;
        total.add(fiveThousand);
        total.add(fiftyThousand);

        System.out.println(total.doubleValue());
    }
}
