package object.creation;

public class CloneExample implements Cloneable {

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

    public static void main(String[] args) throws Exception {
        final CloneExample c = new CloneExample();

        System.out.println(c.clone());

    }

}
