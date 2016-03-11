package com.trvajjala.strategy;

public class ImplTwo extends ImplementationTwo {
    @Override
    public void one() {
        System.out.println("One" + this);
    }

    @Override
    public void two() {

        System.out.println("Two" + this);
    }

    @Override
    public void three() {
        System.out.println("Three" + this);
    }

}
