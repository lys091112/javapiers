package com.xianyue.basictype.concurrent.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author liuhongjun
 * @since 2020-06-08
 */
public class ListHelperDemo {

    public static void main(String[] args) throws Exception {

        ListHelper<Integer> helper = new ListHelper<>();

        Thread thread1 = new Thread(() -> ListHelperDemo.t1(helper));

        Thread thread2 = new Thread(() -> ListHelperDemo.t2(helper));

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(helper.list.size());

    }

    private static void t1(ListHelper<Integer> helper) {

        for (int i = 0; i < 100000; i++) {
            helper.putIfAbsent(i);
        }

    }

    private static void t2(ListHelper<Integer> helper) {

        for (int i = 0; i < 100000; i++) {
            synchronized (helper.list) { // correct way to synchronize

                if (!helper.list.contains(i)) {
                    helper.list.add(i);
                }
            }
        }
    }

    public static class ListHelper<T> {

        public List<T> list = Collections.synchronizedList(new ArrayList<>());

        // 由于t1 锁的是listHelpler的锁 但是t2通过public的list直接进行锁对象
        // 这两个使用的非同一把锁，因此可能会同时有两个线程通过list.contains的检查
        // 然后某个数据被add了两次 从而造成了线程不安全
        public synchronized boolean putIfAbsent(T x) {
            boolean absent = !list.contains(x);

            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }


}
