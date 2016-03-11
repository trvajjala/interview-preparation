package com.trvajjala.bridge;

public class Rectangle extends Shape {

    @Override
    public void colorIt() {
        System.out.println("Reactangle filled with color ");
        colorImplementor.fillColor();
    }

    @Override
    public void draw() {

        System.out.println("Drawing Rectangle with color");
        colorImplementor.fillColor();

    }
}
