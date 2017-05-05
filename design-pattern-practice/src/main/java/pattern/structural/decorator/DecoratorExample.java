package pattern.structural.decorator;

/**
 * decorator design pattern is used to add additional functionality to objects.
 *
 *
 * @author ThirupathiReddy V
 *
 */
public class DecoratorExample {

    public static void main(String[] args) {

	final Pizza pizza = new Suace(new ChilliPizza(new PlainPizza()));

	System.out.println(pizza.getCost());

	System.out.println(pizza.getIngradients());

    }

}
