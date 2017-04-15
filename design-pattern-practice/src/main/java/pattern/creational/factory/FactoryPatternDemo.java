package pattern.creational.factory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;

import pattern.creational.factory.Shape.TYPE;

public class FactoryPatternDemo {

    public static void main(String[] args) {

	Shape shape = ShapeFactory.getShape(TYPE.CIRCLE);
	System.out.println(shape);

	shape = ShapeFactory.getShape(TYPE.CIRCLE);
	System.out.println(shape);

	shape = ShapeFactory.getShape(TYPE.RECTANGLE);
	System.out.println(shape);


	System.out.println(Arrays.toString(TimeZone.getAvailableIDs()));
	Calendar cal=Calendar.getInstance(TimeZone.getTimeZone(TimeZone.getAvailableIDs()[0]));

	System.out.println(cal.hashCode() +" Time "+cal.getTime());

	cal=Calendar.getInstance(TimeZone.getTimeZone(TimeZone.getAvailableIDs()[15 ]));

	System.out.println(cal.hashCode() +" Time "+cal.getTime());


    }
}
