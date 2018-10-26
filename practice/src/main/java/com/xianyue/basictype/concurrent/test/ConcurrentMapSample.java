package com.xianyue.basictype.concurrent.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Xianyue
 */
public class ConcurrentMapSample {
    private static void ifAbsentDemo() {
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap();
        String temp = map.putIfAbsent("yue","computer");
        System.out.println("----- " + temp);
        temp = map.putIfAbsent("yue","runner");
        System.out.println("----- " + temp);
        System.out.println("----- " + map.get("yue"));

        temp = map.put("yue","runner");
        System.out.println("----- " + temp);
        System.out.println("----- " + map.get("yue"));

        String temp2 = map.putIfAbsent("ju","marketing");
        System.out.println("----- " + temp2);
    }

    public static void main(String[] args) {
        ifAbsentDemo();
    }
}
