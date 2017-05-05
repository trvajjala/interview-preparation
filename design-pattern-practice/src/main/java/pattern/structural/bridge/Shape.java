package pattern.structural.bridge;

public abstract class Shape {

    protected Color color;

    protected void setColor(Color colorImplementor) {
	color = colorImplementor;
    }

    protected Color getColorImplementor() {
	return color;
    }

    public abstract void colorIt();

    public abstract void draw();

}
