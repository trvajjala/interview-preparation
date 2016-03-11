package com.trvajjala.strategy;

public abstract class ImplementationOne implements Strategy {

    @Override
    public void solve() {

        one();

        three();
    }

    public abstract void one();

    public abstract void two();

    public abstract void three();
}
