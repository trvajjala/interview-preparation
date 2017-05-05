package pattern.structural.flyweight;

public class FlyweightExample {

    public static void main(String[] args) {

	final String[] colors = { "red", "yellow", "blue", "green", "black" };

	for (int i = 0; i < 100; i++) {

	    Shape shape = FlyweightFactory.getShape("circle");
	    shape.setColor(colors[i % 5]);
	    shape.draw();


	    shape = FlyweightFactory.getShape("triangle");
	    shape.setColor(colors[i % 5]);
	    shape.draw();
	}

    }
}
