package com.xianyue.concurrent;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author langle 模拟分布式多端处理
 */
public class HandleRunnable implements Runnable {

    private Lock                lock = new ReentrantLock();

    private Map<String, String> info;

    public HandleRunnable(Map<String, String> info) {
        this.info = info;
    }

    @Override
    public void run() {
        System.out.println("enter thread. threadId=" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName());
        String key = "agent1";
        if (info.containsKey(key)) {
            System.out.println("contains key");
        }
        try {
            lock.lock();
            Thread.sleep(10000);
            info.put(key, key);
            System.out.println("lock and put");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("out thread. threadId=" + Thread.currentThread().getId() + " name=" + Thread.currentThread().getName());
    }
}
