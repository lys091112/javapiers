package com.xianyue.basictype.concurrent.thread;

import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

class JoinerTest extends Thread {

    Stopwatch watch;

    public JoinerTest(Stopwatch watch) {
        this.watch = watch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                // do nothing
            }
            System.out.println(
                "threadName=" + Thread.currentThread().getName() + "watch=" + watch.elapsed(TimeUnit.MILLISECONDS)
                    + ", index=" + i);
        }
    }

    // join方法会停止自身的运行，从而加入到当前的执行线程队列等待以此运行
    // 因此join方法会阻塞线程的执行
    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        JoinerTest john1 = new JoinerTest(stopwatch);
        JoinerTest john2 = new JoinerTest(stopwatch);
        JoinerTest john3 = new JoinerTest(stopwatch);

        try {
            john1.start();
            john1.join();
            john2.start();
            john2.join();
            john3.start();
            john3.join();
            System.out.println("cost=" + stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
    }
}

