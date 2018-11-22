package com.xianyue.basictype.jvm;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于测试hashmap的非线程安全特性
 */
public class HashMapNotThreadSafe implements Runnable {

  private static AtomicInteger flag = new AtomicInteger(1);

  private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(1);

  public void run() {
    while (flag.get() < 10000000) {
      map.put(flag.get(), flag.get());
      flag.incrementAndGet();
    }
  }
}
