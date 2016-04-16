package pattern.creational.singleton;

public class SingletonDemo {

    public static void main(String[] args) throws Exception {

        MySingleton singleton = MySingleton.getInstance();

        System.out.println(singleton);
        singleton = (MySingleton) singleton.clone();

        System.out.println(singleton);

    }
}
