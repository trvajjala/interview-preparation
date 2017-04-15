package com.trvajjala.bridge;

/**
 * decouples abstraction form its implementation so that both can varies independently.
 *
 * @author ThirupathiReddy V
 *
 */
public class BridgeExample {

    public static void main(String[] args) {

	final Shape rectangleShape = new Rectangle();

	final Shape circleShape = new Circle();

	rectangleShape.setColor(new GreenColorImplementor());

	circleShape.setColor(new BlueColorImplementor());

	circleShape.colorIt();
	circleShape.draw();

	rectangleShape.colorIt();
	rectangleShape.draw();

    }
}
