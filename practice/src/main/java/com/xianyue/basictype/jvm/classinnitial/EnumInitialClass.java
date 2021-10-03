package com.xianyue.basictype.jvm.classinnitial;

/**
 * @author liuhongjun
 * @note
 * @since 2019-04-29
 */
public enum EnumInitialClass {

    SUCCESS("success", "success"),
    FAILED("failed", "failed");

    private String key;
    private String desc;

    private static final String finalParam = printStatic("finalParam");
    private static final String staticParam = printStatic("staticParam");

    static {
        System.out.println("EnumInitialClass: static Block");
    }

    {
        System.out.println("EnumInitialClass: class Block");
    }

    private static String printStatic(String param) {
        System.out.println("EnumInitialClass: " + param);
        return param;
    }

    EnumInitialClass(String key, String desc) {
        this.key = key;
        this.desc = desc;
        System.out.println("EnumInitialClass: new Class " + key);
    }}
