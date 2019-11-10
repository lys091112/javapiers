package com.xianyue.util;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liuhongjun
 * @since 2019-07-04
 */
public class ORateLimiter {

    // 最大等待数量
    private int maxWaitRequest;

    private AtomicLong waitRequests = new AtomicLong(0);

    private RateLimiter rateLimiter;

    public ORateLimiter(int maxWaitRequest, int permitsPerSecond) {
        this.maxWaitRequest = maxWaitRequest;
        this.rateLimiter = RateLimiter.create(permitsPerSecond);
    }

    public ORateLimiter(int maxWaitRequest, long warmPeriod, int permitsPerSecond) {
        this.maxWaitRequest = maxWaitRequest;
        this.rateLimiter = RateLimiter.create(permitsPerSecond, warmPeriod, TimeUnit.MILLISECONDS);
    }

    public boolean acquire() {
        return acquire(1);
    }


    public boolean acquire(int permits) {

        boolean tryAcquire = rateLimiter.tryAcquire(permits);

        if (tryAcquire) {
            rateLimiter.acquire(permits);
            return true;
        }

        if (waitRequests.get() >= maxWaitRequest) {
            return false;
        }
        waitRequests.addAndGet(permits);
        rateLimiter.acquire(permits);
        waitRequests.addAndGet(0 - permits);
        return true;

    }


}
