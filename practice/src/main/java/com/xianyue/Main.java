package com.xianyue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {

    private static transient ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal
        = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:00"));

    public static void main(String[] args) {
        System.out.println(simpleDateFormatThreadLocal.get().format(new Date()));

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
