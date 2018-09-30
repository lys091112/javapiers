package com.xianyue.basictype.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    /**
     * 自定义的test异常码
     */
    TEST(10001, "test exception");

    private int codeId;
    private String message;

    ExceptionCode(int codeId, String message) {
        this.codeId = codeId;
        this.message = message;
    }
}
