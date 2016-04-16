package pattern.creational.singleton;

public class ENUMSingeltonExample {

    public static void main(String[] args) {

        final ENUMInterface s = ENUMSingleton.getInstance();

        System.out.println(s.hashCode());
        System.out.println(ENUMSingleton.getInstance().hashCode());
    }
}
