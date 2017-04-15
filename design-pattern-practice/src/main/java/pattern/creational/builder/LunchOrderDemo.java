package pattern.creational.builder;

public class LunchOrderDemo {


    public static void main(String[] args) {

	LunchOrder.Builder builder=new LunchOrder.Builder();

	LunchOrder lunchOrder= builder.bread("Wheat").dressing("Moyo").meat("Turkey").build();

	System.out.println(lunchOrder);

	builder=new LunchOrder.Builder();
	lunchOrder= builder.bread("White").meat("Lamb").build();

	System.out.println(lunchOrder);
    }
}
