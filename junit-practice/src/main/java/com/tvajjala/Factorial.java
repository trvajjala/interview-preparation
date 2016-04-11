package com.tvajjala;

public class Factorial {

    public Factorial() {
        System.out.println("Factorial Object created");
    }

    public long factorial(long number) {

        if (number < 1) {
            return 1;
        }

        return number * factorial(number - 1);
    }

}
