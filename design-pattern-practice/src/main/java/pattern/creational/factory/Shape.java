package pattern.creational.factory;

public interface Shape {

    void draw();


    static enum TYPE{

	CIRCLE, RECTANGLE, SQUARE
    }
}
