package com.trvajjala.strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println(String.format("Customer paid amount %1.2f by creditcard", amount));
    }

}
