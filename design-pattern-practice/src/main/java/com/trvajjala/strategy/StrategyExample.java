package com.trvajjala.strategy;

public class StrategyExample {

    public static void solve(Strategy strategy) {
        strategy.solve();
    }

    public static void main(String[] args) {
        final Strategy[] st = { new ImplOne(), new ImplTwo() };
        for (final Strategy strategy : st) {
            solve(strategy);
        }
    }

}
