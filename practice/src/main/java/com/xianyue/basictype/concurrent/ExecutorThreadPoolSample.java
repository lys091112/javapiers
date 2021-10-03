package com.xianyue.basictype.concurrent;

import com.alibaba.fastjson.JSON;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author XianYue
 */
public class ExecutorThreadPoolSample implements SignalHandler {
    public static void main(String[] args) {
        ExecutorThreadPoolSample sample = new ExecutorThreadPoolSample();
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        System.out.println(JSON.toJSONString(ManagementFactory.getRuntimeMXBean()));
        // sample.cachePool();
        sample.fixPool();
        // sample.scheduledPool();
        //        sample.singlePool();

        // 通过使用 kill -s SIGUSR2 来触发该信号  接收到该信号后,程序并不会退出，等待下一道信号命令来进行下一步操作
        // 自定义 Signal 用于处理用户发送的 自定义信号,类需要继承 SignalHandler
        Signal s = new Signal("USR2");
        Signal.handle(s, sample);

        // 可以使用 kill -s SIGINT 或者 CTRL-C 来触发该信号
        // 响应交互中断信号,这里写会覆盖系统对于SIGINT的处理，造成jvm不会正常退出
        Signal s1 = new Signal("INT");
        Signal.handle(s1, sample);

        // 用于处理 kill -15信号，
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("lalalalala");
        }));


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
            service.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            // 在index =9时长时间休息，验证kill后，线程是否被成功关闭
                            if (index == 9) {
                                Thread.sleep(15000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " : " + index);
                    }
                });
        }
        System.out.println("------------------------");
        // TODO 用来验证，但执行kill -15 时，保证pool里边的数据都被执行完毕后在退出
        Runtime.getRuntime().addShutdownHook(new Thread(
            () -> {
                System.out.println("come in fixpool hook");
                service.shutdown();
                while (!service.isTerminated()) {
                    try {
                        service.awaitTermination(100, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        // do nothing
                    }
                }
            }));
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
            service.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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

    private void excutorTest() {
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

        ExecutorService executor2 = Executors
            .newFixedThreadPool(4);

        Runnable b = new Runnable() {
            AtomicInteger i = new AtomicInteger(0);

            @Override
            public void run() {
                while (true) {

                    System.out.println("k=" + i.getAndIncrement() + ", thread= " + Thread.currentThread().getId());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        executor2.submit(b);

        executor2.submit(b);

    }

    @Override
    public void handle(Signal signal) {
        System.out.println("receive siganl" + signal.toString() + ", number=" + signal.getNumber());
    }
}
