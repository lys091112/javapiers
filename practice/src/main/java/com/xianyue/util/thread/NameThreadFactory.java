package com.xianyue.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;

public class NameThreadFactory implements ThreadFactory {
  private static final AtomicLong POOL_SEQ = new AtomicLong(0);

  private final AtomicInteger threadNum = new AtomicInteger(0);

  private final String prefix;
  private final boolean daemon;
  @Getter private final ThreadGroup group;

  public NameThreadFactory() {
    this("pool-" + POOL_SEQ.getAndIncrement(), false);
  }

  public NameThreadFactory(String prefix) {
    this(prefix, false);
  }

  public NameThreadFactory(String prefix, boolean daemon) {
    this.prefix = prefix;
    this.daemon = daemon;

    SecurityManager s = System.getSecurityManager();
    this.group = s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
  }

  @Override
  public Thread newThread(Runnable r) {
    String name = prefix + threadNum.getAndIncrement();
    Thread t = new Thread(group, r, name, 0);
    t.setDaemon(daemon);
    return t;
  }
}
