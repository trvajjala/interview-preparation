package com.trvajjala.strategy;

public abstract class ImplementationTwo implements Strategy {

    @Override
    public void solve() {

        two();
        three();
    }

    public abstract void one();

    public abstract void two();

    public abstract void three();
}
