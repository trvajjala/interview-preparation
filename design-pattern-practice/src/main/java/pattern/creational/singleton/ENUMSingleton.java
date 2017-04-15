package pattern.creational.singleton;

public enum ENUMSingleton implements ENUMInterface {

    INSTANCE {

	@Override
	public Object getConnection() {

	    return new Object();
	}


	@Override
	public long getServerTime() {

	    return System.currentTimeMillis();
	}

    };

    public static ENUMSingleton getInstance() {
	return INSTANCE;
    }

    //Without additional Interface U can also write abstract methods here

    //public abstract  long getServerTime();
}
