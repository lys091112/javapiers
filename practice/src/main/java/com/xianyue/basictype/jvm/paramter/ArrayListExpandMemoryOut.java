package com.xianyue.basictype.jvm.paramter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuhongjun
 * @since 2019-11-12
 */
public class ArrayListExpandMemoryOut {

    /**
     * 参考链接：https://mp.weixin.qq.com/s/HKdpmmvJKq45QZdV4Q2cYQ?
     *
     * -Xms300m -Xmx300m -Xmn130m -XX:+UseConcMarkSweepGC
     * -XX:+UseCMSInitatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=90
     *
     * jstat -gcutil pid 1000 2000 用来查看GC情况
     *
     * jmap -heap pid 用来查看当前堆的使用情况
     *
     * jmap -histo pid 查询对象占用情况
     *
     * 内存分配较大，当新生代不足以承载时，会将数据放入到老生代，占满后又占用了大量的新生代， 后台GC线程不停的进行FGC，但是 list.add(..)发生的扩容是浅拷贝，造成老生代数据在新生代存在引用，
     * 由于跨代引用，即新生代的对象引用了老生代的数据，造成老生代无法被垃圾回收 因此会频繁进行fullGC，但是却又不回收，（如果进行手动GC，就会发现垃圾被回收）
     *
     * 也就是说如果在FGC前，进行一次YGC，就可以处理这种情况，所以加上-XX:+CMSScavengeBeforeRemark可以解决该问题
     *
     * 正常情况下，只有当Eden不够使用时，才会进行YGC, 而因为allocated后eden还有空间，所有不会主动触发ygc
     */
    public static void main(String[] args) {
        alloctMemory();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {

        }
        System.out.println("xxxxxx");
        System.gc();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {

        }
    }

    public static void alloctMemory() {
        List<byte[]> list = new ArrayList<>();

        int size = 1024 * 1024 * 280;
        int len = size / (1024 * 20);
        for (int i = 0; i < len; i++) {
            try {

                list.add(new byte[20 * 1024]);
            } catch (OutOfMemoryError e) {
                // do nothing
            }
        }
    }
}
