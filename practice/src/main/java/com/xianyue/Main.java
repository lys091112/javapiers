package com.xianyue;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    public static void main(String[] args) throws Exception {
        double num1 = 50123.12E25;
        System.out.println(String.valueOf(num1));
        BigDecimal bd1 = new BigDecimal(num1);
        System.out.println(bd1.toPlainString());
        System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        HashMap<Long ,Long> map = new HashMap<>();
        map.put(1L,1L);
        map.put(2L,2L);
        map.put(3L,3L);

        FileOutputStream fs = new FileOutputStream("foo.ser");
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(map);
        os.close();

        FileInputStream fi = new FileInputStream("foo.ser");
        ObjectInputStream oi = new ObjectInputStream(fi);
         HashMap<Long ,Long> map2 = (HashMap<Long, Long>)oi.readObject();
        System.out.println(map2);

    }

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
