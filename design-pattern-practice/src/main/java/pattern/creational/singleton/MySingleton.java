package pattern.creational.singleton;

public class MySingleton implements Cloneable {

    private static MySingleton INSTANCE;

    private MySingleton() {
    }

    public static MySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (MySingleton.class) {
                INSTANCE = new MySingleton();
            }
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        throw new CloneNotSupportedException("Cloning is not supported by singleton");
    }
}
