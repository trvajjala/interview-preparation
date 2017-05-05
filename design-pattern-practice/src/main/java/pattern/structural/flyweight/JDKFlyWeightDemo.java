package pattern.structural.flyweight;

public class JDKFlyWeightDemo {


    public static void main(String[] args) {

	final Integer first=Integer.valueOf(10);
	final Integer second=Integer.valueOf(10);
	final Integer third=Integer.valueOf(5);


	System.out.println(System.identityHashCode(first));
	System.out.println(System.identityHashCode(second));
	System.out.println(System.identityHashCode(third));
    }

}
