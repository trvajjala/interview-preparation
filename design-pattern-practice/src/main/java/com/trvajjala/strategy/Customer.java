package com.trvajjala.strategy;

public class Customer {

    public static void main(String[] args) {

        final String paymentType = "card";

        PaymentStrategy paymentStrategy = null;
        if ("cash".equalsIgnoreCase(paymentType)) {
            paymentStrategy = new CashPaymentStrategy();
        } else if ("card".equalsIgnoreCase(paymentType)) {
            paymentStrategy = new CreditCardPaymentStrategy();
        }

        final PaymentContext paymentContext = new PaymentContext();
        paymentContext.setPaymentStrategy(paymentStrategy);
        paymentContext.pay(1000.207);

    }
}
