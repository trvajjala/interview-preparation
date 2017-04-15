package com.tvajjala.enums;
/**
 *
 * @author ThirupathiReddy V
 *
 */
public enum Fruit {

    APPLE("red") {
	@Override
	public void color() {

	}
    },

    MANGO("yellow") {
	@Override
	public void color() {

	}
    };

    private String color;

    private Fruit(String color) {
	this.color = color;
    }

    @Override
    public String toString() {
	return color;
    }

    public abstract void color();
}
