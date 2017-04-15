package com.trvajjala.strategy;

/**
 * Composition over inheritance Strategy design pattern follows composite design
 * pattern
 *
 * @author ThirupathiReddy V
 *
 */
public class CustomerExample {

    public static void main(String[] args) {

	final String paymentType = "card";

	final PaymentContext paymentContext = CustomerExample.getPaymentContext(paymentType);

	paymentContext.pay(1000.207);

    }

    public static PaymentContext getPaymentContext(String paymentType) {

	PaymentStrategy paymentStrategy = null;

	if ("cash".equalsIgnoreCase(paymentType)) {
	    paymentStrategy = new CashPaymentStrategy();
	} else if ("card".equalsIgnoreCase(paymentType)) {
	    paymentStrategy = new CreditCardPaymentStrategy();
	}

	final PaymentContext paymentContext = new PaymentContext();
	paymentContext.setPaymentStrategy(paymentStrategy);
	return paymentContext;
    }
}
