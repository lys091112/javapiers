package com.xianyue.common.stater.inform;

import com.xianyue.common.stater.ResultType;

/**
 * @author liuhongjun
 * @note
 * @since 2019-06-13
 */
public enum InformResultEvent implements ResultType {
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

    public String getName() {
        return name;
    }

    @Override
    public int getFlag() {
        return code;
    }
}
