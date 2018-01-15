package com.xianyue.design.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AggregateDoubleCheck {

    private static volatile AggregateDoubleCheck aggregateDoubleCheck;

    public static AggregateDoubleCheck getInstance() {
        log.info("get instance of aggregate double check");
        if (null == aggregateDoubleCheck) {
            synchronized (AggregateDoubleCheck.class) {
                if (null == aggregateDoubleCheck) {
                    log.info("create aggregate double check");
                    aggregateDoubleCheck = new AggregateDoubleCheck();
                }
            }
        }
        return aggregateDoubleCheck;
    }
}
