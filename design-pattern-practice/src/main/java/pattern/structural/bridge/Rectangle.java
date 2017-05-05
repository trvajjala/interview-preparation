package pattern.structural.bridge;

public class Rectangle extends Shape {

    @Override
    public void colorIt() {
	System.out.println("Reactangle filled with color ");
	color.fillColor();
    }

    @Override
    public void draw() {

	System.out.println("Drawing Rectangle with color");
	color.fillColor();

    }
}
