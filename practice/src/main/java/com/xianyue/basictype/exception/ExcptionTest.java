package com.xianyue.basictype.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcptionTest implements ExceptionAdapter {

    public void test() {
        createException("except");
    }

    public static void main(String[] args) {
        ExcptionTest test = new ExcptionTest();
        try {
            test.test();
        } catch (Exception e) {
            log.error("exception ==> " + e);
        }

        test.test();
    }
}
