package com.xianyue.basictype.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @author Xianyue
 */
public class ActionTrigger {
    static ForkJoinPool pool = new ForkJoinPool(4);

    private static CountTask countTask = null;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        way1();
//        way2();
        way3();
        pool.shutdown();
    }

    public static void way1() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> list = new ArrayList<>();
        CountTask task = null;
        for (int i = 0; i < 100000; i++) {
            task = new CountTask(i);
            Future<Integer> future = pool.submit(task);
            list.add(future);
        }

        int sum = 0;
        for (Future res : list) {
            sum = sum + (int) res.get();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("way1 sum is " + sum);
        System.out.println("way1 time cost is " + (endTime - startTime));

    }

    public static void way2() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> list = new ArrayList<>();
        CountTask task = null;
        for (int i = 0; i < 100000; i++) {
            task = new CountTask(i);
            Future<Integer> future = pool.submit(task);
            task.join();
            list.add(future);
        }

        int sum = 0;
        for (Future res : list) {
            sum = sum + (int) res.get();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("way2 sum is " + sum);
        System.out.println("way2 time cost is " + (endTime - startTime));
    }

    public static void way3() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        List<Future<Integer>> list = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    countTask = new CountTask(i);
                    Future<Integer> future = pool.submit(countTask);
                    list.add(future);
                }
                latch.countDown();
            }
        });
        ExecutorService service2 = Executors.newFixedThreadPool(2);
        service2.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 50000; i < 100000; i++) {
                    countTask = new CountTask(i);
                    Future<Integer> future = pool.submit(countTask);
                    list.add(future);
                }
                latch.countDown();
            }
        });

        latch.await();
        int sum = 0;
        for (Future res : list) {
//            if()
            sum = sum + (int) res.get();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("way3 sum is " + sum);
        System.out.println("way3 time cost is " + (endTime - startTime));
    }

}
