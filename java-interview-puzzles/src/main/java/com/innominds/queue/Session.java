package com.innominds.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Session implements Delayed {

    long seconds;

    Session(long seconds) {
        this.seconds = seconds;
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (seconds - o.getDelay(TimeUnit.SECONDS));
    }

    @Override
    public long getDelay(TimeUnit unit) {// convert to destination unit
        return TimeUnit.SECONDS.convert(seconds, unit);
    }

}
