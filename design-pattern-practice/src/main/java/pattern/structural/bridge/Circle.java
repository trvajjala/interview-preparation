package pattern.structural.bridge;

public class Circle extends Shape {

    @Override
    public void colorIt() {
	System.out.println(" Circle color ");
	color.fillColor();
    }

    @Override
    public void draw() {
	System.out.println("Circle drawing with color ");
	color.fillColor();
    }
}
