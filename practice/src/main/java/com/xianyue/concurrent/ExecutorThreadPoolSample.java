package com.xianyue.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author XianYue
 */
public class ExecutorThreadPoolSample {
    public static void main(String[] args) {
        ExecutorThreadPoolSample sample = new ExecutorThreadPoolSample();
        // sample.cachePool();
        // sample.fixPool();
        // sample.scheduledPool();
        sample.singlePool();
    }

    /**
     * 缓存线程池，无限大小，可以重复利用线程
     */
    private void cachePool() {
        ExecutorService service = Executors.newCachedThreadPool(new OwnThreadFactory("cachePool"));
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " : " + index);
                }
            });
        }
    }

    /**
     * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()*
     */
    private void fixPool() {
        ExecutorService service = Executors.newFixedThreadPool(3, new OwnThreadFactory("fixedPool"));
        for (int i = 0; i < 10; i++) {
            final int index = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " : " + index);
                }
            });
        }
    }

    private void scheduledPool() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2, new OwnThreadFactory("scheduledPool"));
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " delay 1 seconds, and every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    private void singlePool() {
        ExecutorService service = Executors.newSingleThreadExecutor(new OwnThreadFactory("singlePool"));
        for (int i = 0; i < 10; i++) {
            final int index = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + index);
                }
            });
        }
    }

    private void futureExec() {
        Map<String, String> map = new HashMap<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4, 10, 200, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new OwnThreadFactory("own"));
        executor.submit(new HandleRunnable(map));
        Future future = executor.submit(new HandleRunnable(map));
        try {
            future.get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
