package pattern.creational.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
/**
 *  When you serialize across JVMs readResolve will avoid to create new instance at the time of de-serialize
 *
 *  http://thecodersbreakfast.net/index.php?post/2011/05/12/Serialization-and-magic-methods
 *
 *
 * @author ThirupathiReddy V
 *
 */
public class BestSingleton implements Cloneable, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -287105331336830121L;
    public volatile static BestSingleton _instance;// to avoid half initialization

    private BestSingleton() {
	if (_instance != null) {
	    throw new RuntimeException("Please create instance through getInstance() method");
	}
    }

    public static BestSingleton getInstance() {
	if (_instance == null) {
	    synchronized (BestSingleton.class) {// multi-threading
		if (_instance == null) {// double checking
		    _instance = new BestSingleton();
		}
	    }
	}
	return _instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
	throw new CloneNotSupportedException("Cloning is not suppoted.");
    }

    public void doAction() {
	System.out.println(" Executing singleton business methods");
    }


    /* public Object writeReplace() throws ObjectStreamException {
	System.out.println("WriteReplace method gets invoked.");
	return this;
    }*/

    public Object readResolve() throws ObjectStreamException {
	System.out.println(" ReadResolve method gets executed ");
	return _instance; // while deserializing without this method it creates new object
    }

}

class SingletonTest {

    public static void main(String[] args) throws Exception {

	final BestSingleton bestSingleton = BestSingleton.getInstance();

	System.out.println("  bestSingleton : " + bestSingleton.hashCode());

	final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("a.ser"));
	oos.writeObject(bestSingleton);
	oos.close();

	final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("a.ser"));
	final BestSingleton serObj = (BestSingleton) ois.readObject();
	ois.close();

	System.out.println("  serObject : " + serObj.hashCode());

    }
}
