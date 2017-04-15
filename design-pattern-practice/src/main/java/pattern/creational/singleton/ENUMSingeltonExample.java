package pattern.creational.singleton;

public class ENUMSingeltonExample {

    public static void main(String[] args) {

	ENUMInterface instance = ENUMSingleton.getInstance();


	System.out.println("Instance Created "+instance.hashCode());
	System.out.println(instance.getServerTime());


	instance = ENUMSingleton.getInstance();

	System.out.println("Is New Instance Created?  "+instance.hashCode());

    }
}
