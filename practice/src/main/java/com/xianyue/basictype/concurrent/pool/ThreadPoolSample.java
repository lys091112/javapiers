package com.xianyue.basictype.concurrent.pool;

import com.google.common.util.concurrent.RateLimiter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuhongjun
 * @since 2020-07-13
 */
public class ThreadPoolSample {


    private ThreadPoolExecutor executor = new ThreadPoolExecutor(128, 256, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000));

    private RateLimiter limiter = RateLimiter.create(100);


    public void createFasterThanConsumer() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; ++i) {
            list.add(i);
        }

        for (Integer i : list) {
//            limiter.acquire(); // 在这里没问题，控制消费速率，1分钟内肯定能消费完入队的数据
            try {
                executor.execute(
                    () -> {
                        // 在这种情况下，由于没有对create限速，从而造成10000个数据快速写入executor.array(1000)
                        // 造成其中的9000个被拒绝(默认策略）
                        limiter.acquire();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("i = " + i);
                    });
            } catch (Exception e) {
                e.printStackTrace();
                ;
            }
        }
    }
}
