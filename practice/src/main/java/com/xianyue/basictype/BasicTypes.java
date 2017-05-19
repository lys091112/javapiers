package com.xianyue.basictype;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Xianyue
 */
public class BasicTypes {


    public static void main(String[] args) {
        integerTest();

        countTest();
    }
    /**
     * 在java中，-128~127这些数字在系统中有共享一份存储，当数值超过128后没有。
     * Integer.valueOf 返回是的一个Integer对象
     * Integer.parseInt返回的是一个int值
     *
     */
    private static void integerTest() {
        System.out.println("---------integerTest-----------------");
        System.out.println(Integer.valueOf("127") == Integer.valueOf("127"));
        System.out.println(Integer.valueOf("128") == Integer.valueOf("128"));
        //比较时，会被转化为int后在进行比较
        System.out.println(Integer.parseInt("128") == Integer.valueOf("128"));
    }

    //并发计数器，这和旧的Atomic类的区别在于，当CAS指令因为竞争而失败时，Adder不会一直占着CPU，而是为当前线程分配一个内部cell对象来存储 计数器的增量。
    // 然后这个值和其他待处理的cell对象一起被加到intValue()的结果上。这减少了反复使用CAS指令或阻塞其他线程的可能性
    public static void countTest() {
        System.out.println("------------countTest--------------");
        LongAdder counter = new LongAdder();
        counter.increment();
        counter.add(10);
        System.out.println(counter.intValue());
    }
}
