package com.xianyue.design.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        log.info("enum singleton ");
        AggregateEnum aggregateEnum = AggregateEnum.getInstance();

        Thread.sleep(3000);
        log.info("private singleton");
        AggregateData aggregateData = AggregateData.getInstance();


        Thread.sleep(3000);
        log.info("double check");
        AggregateDoubleCheck aggregateDoubleCheck = AggregateDoubleCheck.getInstance();
        aggregateDoubleCheck = AggregateDoubleCheck.getInstance();
        aggregateDoubleCheck = AggregateDoubleCheck.getInstance();

    }
}
