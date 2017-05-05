package pattern.structural.bridge;

public class GreenColor implements Color {

    @Override
    public void fillColor() {
	System.out.println("Filling with Green");
    }
}
