package com.xianyue.basictype.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Xianyue
 */
public class CASLock {
    private AtomicInteger reflashFlag = new AtomicInteger(1);
    AtomicInteger num = new AtomicInteger(0);
    CountDownLatch latch = new CountDownLatch(200);
    Random random = new Random();

    public void reflash() throws InterruptedException {
        int flag = reflashFlag.get();
        Thread.sleep(100);
        if (reflashFlag.compareAndSet(flag, flag + 1)) {
            num.getAndIncrement();
            System.out.printf("i am in. flag is %d, now is %d ", flag, reflashFlag.get());
            System.out.println();
        } else {
            System.out.printf("failed. falge is %d, now is %d", flag, reflashFlag.get());
            System.out.println();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        CASLock lock = new CASLock();
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 200; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.reflash();
                        lock.latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        lock.latch.await();
        System.out.println("--------- " + lock.num.get());
    }
}
