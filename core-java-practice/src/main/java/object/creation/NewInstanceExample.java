package object.creation;

public class NewInstanceExample {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        final Class clazz = Class.forName(NewInstanceExample.class.getName());

        final NewInstanceExample cloneExample = (NewInstanceExample) clazz.newInstance();

        System.out.println(cloneExample);
    }
}
