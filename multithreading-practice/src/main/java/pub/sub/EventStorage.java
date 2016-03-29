package pub.sub;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ThirupathiReddy V
 *
 */
public class EventStorage {

    private final int maxSize;
    private final List<Date> events;

    public EventStorage() {
        maxSize = 10;
        events = new LinkedList<Date>();
    }

    public int getMaxSize() {
        return maxSize;
    }

    public List<Date> getEvents() {
        return events;
    }

    public synchronized Date borrowEvent() {

        while (events.size() == 0) {
            try {
                wait();
            } catch (final InterruptedException e) {
            }
        }
        final Date element = ((LinkedList<Date>) events).poll();
        System.err.println("Returning event " + element + "  size : " + events.size());
        notifyAll();
        return element;

    }

    public synchronized void addEvent() {
        while (events.size() == maxSize) {
            try {
                wait();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
        ((LinkedList<Date>) events).offer(new Date());
        System.out.println("Adding events to storage. now size is  " + events.size());
        notifyAll();

    }

}
