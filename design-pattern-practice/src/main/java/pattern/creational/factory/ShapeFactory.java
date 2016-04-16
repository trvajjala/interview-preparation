package pattern.creational.factory;

/**
 * objects are created without exposing object creation logic outside.<br>
 * refer newly created objects using interface.
 *
 * @author ThirupathiReddy V
 *
 */
public class ShapeFactory {

    public static Shape getShape(String type) {

        if ("Circle".equalsIgnoreCase(type)) {
            return new Circle();
        } else if ("Square".equalsIgnoreCase(type)) {
            return new Square();
        } else if ("Rectangle".equalsIgnoreCase(type)) {
            return new Rectangle();
        }
        return null;
    }

    public static void main(String[] args) {
        final Shape shape = ShapeFactory.getShape("Circle");
        System.out.println(shape);
    }

}
