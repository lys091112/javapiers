package com.xianyue.beauty;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public abstract class CachedGauge<T> {
    private final Clock      clock;
    private final AtomicLong reloadAt;
    private final long       timeoutNS;

    private volatile T       value;
    protected CachedGauge(long timeout, TimeUnit timeoutUnit) {
        this(new Clock(), timeout, timeoutUnit);
    }

    /**
     * Creates a new cached gauge with the given clock and timeout period.
     */
    protected CachedGauge(Clock clock, long timeout, TimeUnit timeoutUnit) {
        this.clock = clock;
        this.reloadAt = new AtomicLong(0);
        this.timeoutNS = timeoutUnit.toNanos(timeout);
    }

    /**
     * Loads the value and returns it.
     *
     */
    protected abstract T loadValue();

    public T getValue() {
        if (shouldLoad()) {
            this.value = loadValue();
        }
        return value;
    }

    //通过不停的检测这个方法来来判断是否可以获取值
    private boolean shouldLoad() {
        for (;;) {
            final long time = clock.getTick();
            final long current = reloadAt.get();
            if (current > time) {
                return false;
            }
            if (reloadAt.compareAndSet(current, time + timeoutNS)) {
                return true;
            }
        }
    }

    public static class Clock {
        private long time;
        public long getTick() {
            return System.nanoTime();
        }

    }
}
