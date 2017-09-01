package com.xianyue.basictype.exception;

public interface ExceptionAdapter {

    default String createException(String except) {
        if ("except".equalsIgnoreCase(except)) {
            throw new UnsupportedOperationException();
        }
        return "success";

    }
}
