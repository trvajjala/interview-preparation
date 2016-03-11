package com.trvajjala.decorator;

public class ChilliPizza extends PizzaDecorator {

    Pizza pizza;

    public ChilliPizza(Pizza pizza) {
        super(pizza);
        this.pizza = pizza;
    }

    @Override
    public double getCost() {

        return 1.4 + pizza.getCost();
    }

    @Override
    public String getIngradients() {

        return "[ Chilli ] + " + pizza.getIngradients();
    }
}
