package com.trvajjala.strategy;

public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println(String.format("Customer paid amount %1.2f by cash", amount));
    }
}
