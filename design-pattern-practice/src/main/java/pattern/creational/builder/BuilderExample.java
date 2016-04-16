package pattern.creational.builder;

/**
 * if the object is having too many properties.it is not advisable to have constructor.<br>
 * there can be some properties optional. in this case builder design pattern is more relevant.
 *
 * @author ThirupathiReddy V
 *
 */
public class BuilderExample {

    public static void main(String[] args) {

        final Address address = new Address.Builder().withFirstname("ThirupathiReddy").withMobile("9000211024").withCity("Hyd").withPostalCode("5000049")
                .createAddress();

        System.out.println(address);
    }
}
