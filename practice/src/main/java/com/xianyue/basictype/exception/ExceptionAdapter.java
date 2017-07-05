package com.xianyue.basictype.exception;

public interface ExceptionAdapter {

    public default String createException(String except) {
        if ("except".equalsIgnoreCase(except)) {
            throw new UnsupportedOperationException();
        }
        return "success";

    }
}
