package pattern.creational.factory;

/**
 * objects are created without exposing object creation logic outside.<br>
 * refer newly created objects using interface.
 *
 * @author ThirupathiReddy V
 *
 */
public class ShapeFactory {

    public static Shape getShape(Shape.TYPE type) {

	if ("CIRCLE".equalsIgnoreCase(type.name())) {
	    return new Circle();
	} else if ("SQUARE".equalsIgnoreCase(type.name())) {
	    return new Square();
	} else if ("RECTANGLE".equalsIgnoreCase(type.name())) {
	    return new Rectangle();
	}

	throw new RuntimeException("Invalid argument passed ");
    }



}
