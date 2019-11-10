package com.xianyue.basictype.jvm.classinnitial;

public class ChildClass extends SuperClass{

    static {
        System.out.println("ChildClass innitial");
    }
}
