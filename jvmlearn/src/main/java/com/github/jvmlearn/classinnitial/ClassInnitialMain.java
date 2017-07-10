package com.github.jvmlearn.classinnitial;

public class ClassInnitialMain {

    public static void main(String[] args) {
        innitial03();
    }


    public static void innitial01() {
        System.out.println(ChildClass.value);  // get static , initial
    }

    public static void innitial02() {
        ChildClass[] childClasses = new ChildClass[10]; // array, do nothing
    }

    public static void innitial03() {
        System.out.println(ConstValue.HELLO_WORLD);
    }
}
