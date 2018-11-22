package com.xianyue.basictype.jvm.classinnitial;

public class SuperClass {

    static {
        System.out.println("SuperClass innitial");
    }

    public static int value = 6;
}
