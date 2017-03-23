package com.xianyue.concurrent.test;

import com.xianyue.concurrent.OwnThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Xianyue
 * 死循环的时候，会使cpu到达100%
 */
public class EndLessLoop {


    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor(new OwnThreadFactory("deadloop"));
        service.execute(() -> {
            int i = Integer.MIN_VALUE;
            while (true) {
                i++;
            }
        });
        ScheduledExecutorService service1 = Executors.newScheduledThreadPool(2, new OwnThreadFactory("scheduled"));
        service1.scheduleAtFixedRate(() -> {
            System.out.println(Thread.currentThread().getName() + " delay 1 second");
        }, 100, 100, TimeUnit.MILLISECONDS);
    }
}
