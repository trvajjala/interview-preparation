package methodcallig;

public class MethodCalling {

    public MethodCalling() {
        world();
    }

    public void world() {
        System.out.println("world");
    }

    public static void hello() {
        System.out.println("Hello Static");
    }

    public static void main(String[] args) {

        System.out.println("Hello");

        new MethodCalling();

    }
}
