package com.xianyue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors
                .newFixedThreadPool(4);
        List<Runnable> singleWorkers = IntStream.range(0, 4).mapToObj((int t) -> {
                    Runnable a = new Runnable() {
                        AtomicInteger i = new AtomicInteger(0);

                        @Override
                        public void run() {
                            System.out.println("i = " + i.getAndIncrement());
                        }
                    };
                    executor.submit(a);
                    return a;
                }
        ).collect(Collectors.toList());

//        ExecutorService executor2 = Executors
//                .newFixedThreadPool(4);

//        Runnable b = new Runnable() {
//            AtomicInteger i = new AtomicInteger(0);
//
//            @Override
//            public void run() {
//                while (true) {
//                    System.out.println("k=" + i.getAndIncrement() + ", thread= " + Thread.currentThread().getId());
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        executor2.submit(b);


        System.out.println(System.getProperty("java.io.tmpdir"));

        ScheduledExecutorService pools = Executors.newScheduledThreadPool(2);
        pools.scheduleAtFixedRate(new Runnable() {
            AtomicInteger flag = new AtomicInteger(1);

            @Override
            public void run() {
                System.out.println("now flag is " + flag.incrementAndGet());
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    // 在非并行的lambda处理中，所有的处理过程都是在一个线程中执行
    public static void lambdaExecThread() {
        List<String> str = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            str.add("hello" + i);
        }

        Set<Long> threadIds = new HashSet<>();
        str.forEach(
                s -> {
                    threadIds.add(Thread.currentThread().getId());
                    System.out.println(s + ", Thread ---> " + Thread.currentThread().getId());
                });

        Set<Long> threadId2s = new HashSet<>();
        str.stream()
                .map(t -> t + "ha")
                .collect(Collectors.toList())
                .forEach(
                        s -> {
                            threadId2s.add(Thread.currentThread().getId());
                            System.out.println(s + ", Thread " + "---> " + Thread.currentThread().getId());
                        });

        Set<Long> threadId3s = new HashSet<>();
        str.parallelStream()
                .map(
                        t -> {
                            threadId3s.add(Thread.currentThread().getId());
                            return t + "ha" + Thread.currentThread().getId();
                        })
                .collect(Collectors.toList())
                .forEach(
                        s -> System.out.println(s + ", " + "Thread " + "---> " + Thread.currentThread().getId()));
        System.out.println(threadIds);
        System.out.println(threadId2s);
        System.out.println(threadId3s);
    }
}
