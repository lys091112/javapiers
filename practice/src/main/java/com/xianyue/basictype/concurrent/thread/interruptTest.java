package com.xianyue.basictype.concurrent.thread;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liuhongjun
 * @since 2020-10-30
 */
public class interruptTest {

    public static void main(String[] args) throws InterruptedException {
        FutureTask<String> future =
            new FutureTask(
                () -> {
                    while (true) {
                        // do nothing
                    }
//                    try {
//                        Thread.sleep(10000000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }, "");
        Thread t1 = new Thread(future);
        t1.start();

        Thread t2 =
            new Thread(
                () -> {
                    try {
                        System.out.println(LocalTime.now() + " -----in-------");
                        System.out.println(future.get());
                        System.out.println(LocalTime.now() + "-----out-------");
                    } catch (InterruptedException e) {
                        System.out.println(LocalTime.now() + " interrupt");
                        future.cancel(true);
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
        t2.start();

        //
        Thread.sleep(1000);
        t1.interrupt(); // t1的interrupt 虽然通知t1进行中断，但是因为他一直在数据处理中，因为无法响应中断，造成t2线程的持续等待。 所以当t2调用中断时，t2会中断自身等待
        Thread.sleep(3000);
        t2.interrupt();
    }
}
