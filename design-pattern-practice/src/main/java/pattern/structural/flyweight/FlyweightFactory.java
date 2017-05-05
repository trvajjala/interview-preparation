package pattern.structural.flyweight;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * shapeFactory will return object with intrinsic state only. extrinsic state is applied on the fly.
 *
 * @author ThirupathiReddy V
 *
 */
public class FlyweightFactory {

    static Map<String, Shape> shapeFactoryMap = Collections.synchronizedMap(new HashMap<String, Shape>());

    public static Shape getShape(String shapeType) {

        Shape shape = null;

        if ("circle".equalsIgnoreCase(shapeType)) {

            shape = shapeFactoryMap.get(shapeType);

            if (shape == null) {
                final Lock lock = new ReentrantLock();
                lock.lock();
                shape = new Circle();
                shapeFactoryMap.put(shapeType, shape);
                lock.unlock();
            }

        } else if ("triangle".equalsIgnoreCase(shapeType)) {
            shape = shapeFactoryMap.get(shapeType);
            if (shape == null) {
                final Lock lock = new ReentrantLock();
                lock.lock();
                shape = new Triangle();
                shapeFactoryMap.put(shapeType, shape);
                lock.unlock();
            }
        }

        return shape;
    }

}
