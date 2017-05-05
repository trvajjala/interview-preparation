package concurrency.pub.sub;

public class PubSubExample {

    public static void main(String[] args) {
        final EventStorage eventStorage = new EventStorage();

        final Producer producer = new Producer(eventStorage);
        producer.start();

        final Consumer consumer = new Consumer(eventStorage);
        consumer.start();

    }
}
