package com.xianyue.common.stater.record;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-13
 */
public enum InformResultEvent {
    // 初始无状态
    NONE(0, "无"),
    // 成功
    SUCCESS(1, "成功"),
    //失败
    FAILED(2, "失败"),
    // 中间态，没成功，但不算失败
    INTERMEDIATE(3, "中间态");


    private int code;
    private String name;

    InformResultEvent(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }}
