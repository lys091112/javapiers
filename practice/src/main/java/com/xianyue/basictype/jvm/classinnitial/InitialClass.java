package com.xianyue.basictype.jvm.classinnitial;

/**
 * @author liuhongjun
 * @note
 * @since 2019-04-29
 *
 * 先初始化静态变量 --> 静态代码块 --> 类变量 --> 类代码块
 */
public class InitialClass {

    private int param = print("param");

    private static final String finalParam = printStatic("finalParam");
    private static String staticParam = printStatic("staticParam");

    private int print(String param) {
        System.out.println("InitialClass: " + param);
        return 0;
    }

    static {
        System.out.println("InitialClass: static blokc 01");
    }

    {
        System.out.println("InitialClass: i am the block!");
    }

    static {
        System.out.println("InitialClass: static blokc 02");
    }

    private static String printStatic(String param) {
        System.out.println("InitialClass: " + param);
        return param;
    }

}
