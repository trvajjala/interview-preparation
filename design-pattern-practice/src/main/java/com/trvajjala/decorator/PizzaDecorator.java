package com.trvajjala.decorator;

public abstract class PizzaDecorator implements Pizza {

    private final Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }

    @Override
    public String getIngradients() {
        return pizza.getIngradients();
    }
}
