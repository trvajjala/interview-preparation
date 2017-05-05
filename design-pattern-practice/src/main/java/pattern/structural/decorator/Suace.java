package pattern.structural.decorator;

public class Suace extends PizzaDecorator {

    private final Pizza pizza;

    public Suace(Pizza pizza) {
        super(pizza);

        this.pizza = pizza;
    }

    @Override
    public double getCost() {
        return 2.9 + pizza.getCost();
    }

    @Override
    public String getIngradients() {
        return "[  Sauce  ] + " + pizza.getIngradients();
    }
}
