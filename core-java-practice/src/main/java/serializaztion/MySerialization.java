package serializaztion;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class MySerialization implements Serializable, ObjectInputValidation {

    /**
     *
     */
    private static final long serialVersionUID = 6493064148388742162L;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void validateObject() throws InvalidObjectException {
        System.out.println("Validating Object ");
    }

    public Object readResolve() throws ObjectStreamException {
        System.out.println("ReadResolve method gets invoked.");
        return this;
    }

    public Object writeReplace() throws ObjectStreamException {
        System.out.println("WriteReplace method gets invoked.");
        return this;
    }

    // This two method must be private
    private void writeObject(ObjectOutputStream oos) throws IOException {
        System.out.println("WriteObject method gets invoked.");
        oos.defaultWriteObject();
    }

    // This two method must be private
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        System.out.println("ReadObject method gets invoked.");
        ois.registerValidation(this, 0);
        ois.defaultReadObject();
    }

}

class SerTest {

    public static void main(String[] args) throws Exception {

        final MySerialization ser = new MySerialization();
        ser.setName("Thiru");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.ser"))) {
            oos.writeObject(ser);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.ser"))) {
            final MySerialization ss = (MySerialization) ois.readObject();
            System.out.println(ss.getName());
        }

        deserialize(serialize(ser));

    }

    private static byte[] serialize(Object o) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        oos.close();
        return baos.toByteArray();
    }

    private static Object deserialize(byte[] bytes) throws ClassNotFoundException, IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        final ObjectInputStream ois = new ObjectInputStream(bais);
        final Object o = ois.readObject();
        ois.close();
        return o;
    }
}
