package com.xianyue.basictype.concurrent;

import com.google.common.util.concurrent.Uninterruptibles;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhongjun
 * @since 2019-07-08
 */
public class ThreadInterrruptWait {


    public void unInterruptWait() {
        long start = System.nanoTime();
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(new Waiter(Thread.currentThread(), latch)).start();

        // 不允许中断等待
        Uninterruptibles.awaitUninterruptibly(latch, 5, TimeUnit.MINUTES);

        System.out.println("end. cost=" + TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start));
    }

    public static void main(String[] args) {
        ThreadInterrruptWait wait = new ThreadInterrruptWait();
        wait.unInterruptWait();
    }

    public static class Waiter implements Runnable {

        private Thread waitThread;
        private CountDownLatch latch;

        public Waiter(Thread waitThread, CountDownLatch latch) {
            this.waitThread = waitThread;
            this.latch = latch;
        }

        @Override
        public void run() {
            waitThread.interrupt();
            try {
                Thread.sleep(200000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
