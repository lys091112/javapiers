package com.xianyue.basictype.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author  XianYue
 */
public class SemaphoreSample implements Runnable{
    private CountDownLatch latch;
    private Semaphore semaphore;

    public SemaphoreSample(Semaphore semaphore,CountDownLatch latch) {
        this.semaphore = semaphore;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(1000);
            System.out.println("now can use count: " + semaphore.availablePermits() + ", name: " + Thread.currentThread().getName());
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        int count = 10;
        Semaphore semaphore = new Semaphore(3);
        CountDownLatch latch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < count; i++) {
           service.execute(new SemaphoreSample(semaphore,latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();

    }
}
