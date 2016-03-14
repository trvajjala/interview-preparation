package wildcard;

import java.util.ArrayList;
import java.util.List;

public class WildExtends {

    public static void main(String[] args) {

        final List<Animal> list = new ArrayList<>();

        list.add(new Dog());
        copy(list);

        final Cat animal = new Cat();
        list.add(animal);
        copy(list);

        save(list);
    }

    public static void copy(List<? super Dog> list) {

        System.out.println(list);
    }

    public static void save(List<? extends Animal> list) {

        System.out.println(list);
    }

}
