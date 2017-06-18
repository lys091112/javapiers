package com.xianyue.date;

import com.xianyue.util.thread.NameThreadFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 高并发场景下System.currentTimeMillis()的性能问题的优化
 * System.currentTimeMillis()的调用比new一个普通对象要耗时的多
 * System.currentTimeMillis()之所以慢是因为去跟系统打了一次交道
 * 后台定时更新时钟，JVM退出时，线程自动回收
 * copy from <href https://github.com/ltsopensource/light-task-scheduler>
 *
 * 主要用途： 当有多个线程在高并发情况下使用sysem.currentTimeMills()时，可以使用这个类代替。
 *
 * //TODO need a demo to test
 */
public class SystemClock {

  private AtomicLong now;
  private long period;

  private SystemClock(long period) {
    this.period = period;
    this.now = new AtomicLong();
    scheduleUpdateTime();
  }

  private static class TimeHolder {

    private static final SystemClock INSTANCE = new SystemClock(1);
  }

  public SystemClock instance() {
    return TimeHolder.INSTANCE;
  }

  private void scheduleUpdateTime() {
    ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
        new NameThreadFactory("systemClock", true));
    executorService.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), period, period,
        TimeUnit.MILLISECONDS);
  }

  private long currentTimeMillis() {
    return now.get();
  }

  public long now() {
    return instance().currentTimeMillis();
  }

}
