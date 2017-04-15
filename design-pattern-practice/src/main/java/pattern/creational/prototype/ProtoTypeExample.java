package pattern.creational.prototype;
//@formatter:off
/**
 * <code>
 * Registry contains costly objects which were created at the time of
 * initialization. <br>
 *
 * After that if we need new object we simply clone .which is totally new Object<br>
 *
 * When it cloned. It is pre-initialized with the actual object properties.
 * Later you can change required properties instead of all the attributes.
 *<br>
 *</code>
 * @author ThirupathiReddy V
 *
 */
//@formatter:on

public class ProtoTypeExample {

    public static void main(String[] args) throws CloneNotSupportedException {

	final Registry registry = new Registry();

	Item movie = registry.getItem("movie");

	System.out.printf("Movie  : %s .  title : %s ,  name : %s , price %s \n", movie, movie.getTitle(), movie.getName(), movie.getCost());

	movie = registry.getItem("movie"); // hashCode is different which means it is completely new object
	//but it is preInitialized with all the properties of main object

	System.out.printf("Movie  : %s .  title : %s ,  name : %s , price %s \n", movie, movie.getTitle(), movie.getName(), movie.getCost());

    }

}
