package com.xianyue.basictype.jvm.classinnitial;

public class ClassInnitialMain {

    public static void main(String[] args) {
        innitial01();
        innitial02();
        innitial03();

        System.out.println("initialclass");
        InitialClass initialClass = new InitialClass();

        System.out.println("initEnumClass");
        EnumInitialClass enumInitialClass = EnumInitialClass.SUCCESS;
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
