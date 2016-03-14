package com.trvajjala.visitor;

public abstract class Product {

    protected int price;

    public abstract void accept(ShoppingMallVisitor visitor);

    public int getPrice() {

        return price;
    }
}
