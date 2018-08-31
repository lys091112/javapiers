package com.github.jvmlearn.classinnitial;

public class ConstValue {

    static {
        System.out.println("ConstValue innitial");
        String c = "ccccc";
        String a = "aaaaaa";
        String b = "bbbbb";
        String d = a + b;
    }

    //被当作一个常量放置到常量池中，不会触发初始化
    public static final String HELLO_WORLD = "hello world";
}
