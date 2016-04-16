package pattern.creational.objectpool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Generic abstract objectPool to create and re-use specific number of objects.
 *
 * @author ThirupathiReddy V
 *
 * @param <T>
 */
public abstract class ObjectPool<T> {

    private ConcurrentLinkedQueue<T> pool;

    private ScheduledExecutorService executorService;

    protected abstract T createObject();

    public ObjectPool() {
    }

    protected void prepare(int minCount, int maxCount, long validationInterval) {
        initialize(maxCount);
        executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleAtFixedRate(() -> {
            System.err.println("Running validation service " + validationInterval);
            final int size = pool.size();
            if (size < minCount) {
                System.err.println("Pool is drained " + size);
                final int finalSize = size + minCount;
                for (int i1 = 0; i1 < finalSize; i1++) {
                    pool.add(createObject());
                }
            } else if (size > maxCount) {
                final int toRemove = maxCount - size;
                for (int i2 = 0; i2 < toRemove; i2++) {
                    System.err.println("polling ");
                    pool.poll();
                }
            }
        }, 0, validationInterval, TimeUnit.SECONDS);

    }

    public void initialize(final int minObjects) {

        pool = new ConcurrentLinkedQueue<T>();

        for (int i = 0; i < minObjects; i++) {
            pool.add(createObject());
        }

    }

    public T acquire() {
        T instance = pool.poll();

        if (instance == null) {
            instance = createObject();// TODO: it may be varies.
            pool.offer(instance);
        }

        return instance;
    }

    public void release(T object) {

        if (object != null) {
            pool.offer(object);// sending back to queue
        }
    }

    public int getPoolSize() {

        return pool.size();
    }

}
