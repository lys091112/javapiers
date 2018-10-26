package com.xianyue.basictype.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author  Xianyue
 **/
public class OwnThreadFactory implements ThreadFactory {
    private String name;
    private AtomicInteger i = new AtomicInteger();

    public OwnThreadFactory() {
    }

    public OwnThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name + i.getAndIncrement());
        return thread;
    }
}
