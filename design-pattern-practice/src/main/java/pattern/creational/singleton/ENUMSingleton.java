package pattern.creational.singleton;

public enum ENUMSingleton implements ENUMInterface {

    INSTANCE {
        @Override
        public Object getConnection() {

            return new Object();
        }
    };

    public static ENUMSingleton getInstance() {
        return INSTANCE;
    }
}
