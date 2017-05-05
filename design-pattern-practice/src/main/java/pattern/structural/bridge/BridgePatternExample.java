package pattern.structural.bridge;

/**
 * Decouples abstraction form its implementation so that both can varies independently
 *
 * @author ThirupathiReddy V
 *
 */
public class BridgePatternExample {

    public static void main(String[] args) {

	final Shape rectangle = new Rectangle();
	final Shape circle = new Circle();


	rectangle.setColor(new GreenColor());
	circle.setColor(new BlueColor());

	circle.colorIt();
	circle.draw();

	rectangle.colorIt();
	rectangle.draw();

    }
}
