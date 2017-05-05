package pattern.structural.decorator;

public class PlainPizza implements Pizza {

    @Override
    public double getCost() {
        return 1.0;
    }

    @Override
    public String getIngradients() {
        return "[ Plain pizza ]";
    }
}
