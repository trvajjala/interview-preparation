package com.trvajjala.flyweight;

public class Circle implements Shape {

    private String color;

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public void draw() {
        System.out.println("Circle with color : " + color + " hashcode " + hashCode());
    }
}
