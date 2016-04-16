package pattern.creational.prototype;

public class ProtoTypeExample {

    public static void main(String[] args) throws CloneNotSupportedException {

        final Chess chess = new Chess();
        chess.setCount(100);

        final Chess clone = (Chess) chess.clone();
        chess.setCount(003);
        System.out.println(clone.getCount());
    }
}
