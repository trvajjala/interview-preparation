package com.trvajjala.bridge;

public class Circle extends Shape {

    @Override
    public void colorIt() {
        System.out.println(" Circle color ");
        colorImplementor.fillColor();
    }

    @Override
    public void draw() {
        System.out.println("Circle drawing with color ");
        colorImplementor.fillColor();
    }
}
