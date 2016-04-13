package com.innominds.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedQueueExample {

    public static void main(String[] args) throws Exception {

        final DelayQueue<Delayed> dq = new DelayQueue<Delayed>();

        dq.add(new Session(2));

        System.out.println(dq.size());

        TimeUnit.SECONDS.sleep(3);

        System.out.println(dq.size());

    }

}
