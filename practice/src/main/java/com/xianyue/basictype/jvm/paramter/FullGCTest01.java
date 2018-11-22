package com.xianyue.basictype.jvm.paramter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * copy from: https://mp.weixin.qq.com/s?__biz=MzA3MTk3NjA0NA==&mid=2648706047&idx=1&sn=2a5b86c8a000fb130a2f9fc07da697bc&chksm=8731fefcb04677eae07bb29869edf89af0a99177161592a9961821efefd17509324f9c8411d1&mpshare=1&scene=1&srcid=&pass_ticket=dxyPLjrDzNCC6tjsdEddPSr9YoH3arrMNAkeQ50g64%2BqfmCSjZR%2BI3ITCPLfK2up#rd
 * 线程池通过submit方式提交任务，会把Runnable封装成FutureTask。 直接导致了Runnable重写的toString方法在afterExecute统计的时候没有起到我们想要的作用，
 * 最终导致几乎每一个任务（除非hashCode相同）就按照一类任务进行统计。所以这个metricsMap会越来越大，调用metrics接口的时候，会把该map转成一个字符返回
 *
 * TODO 如果不用Runnable ，是否有其他方式可以避免submit execute的使用限制
 */
public class FullGCTest01 {

    /**
     * 统计各类任务已经执行的数量 此处为了简化代码，只用map来代替metrics统计
     */
    private static Map<String, AtomicInteger> metricsMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>()) {
            /**
             * 统计各类任务执行的数量
             * @param r
             * @param t
             */
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                metricsMap.compute(r.toString(),
                    (s, atomicInteger) -> new AtomicInteger(atomicInteger == null ? 0 : atomicInteger.incrementAndGet()));
            }
        };            /**
         * 源源不断的任务添加进线程池被执行
         */
        for (int i = 0; i < 1000; i++) {
            threadPoolExecutor.submit(new SimpleRunnable());
        }
        Thread.sleep(1000 * 2);
        System.out.println(metricsMap);
        threadPoolExecutor.shutdownNow();
    }


    static class SimpleRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("SimpleRunnable execute success");
        }

        /**
         * 重写toString用于统计任务数
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName();
        }
    }
}