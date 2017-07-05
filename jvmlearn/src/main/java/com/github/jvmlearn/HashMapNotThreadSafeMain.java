package com.github.jvmlearn;

/**
 * 死循环后，通过jps找出程序的pid
 * 然后通过jstack ${pid} 来查询当前的线程状态
 */
public class HashMapNotThreadSafeMain {

  public static void main(String[] args) {
    Runnable runnable = new HashMapNotThreadSafe();
    for (int i = 0; i < 10; i++) {
      Thread t1 = new Thread(runnable);
      t1.start();
    }

    System.out.println("--------------------> ");
  }
}
