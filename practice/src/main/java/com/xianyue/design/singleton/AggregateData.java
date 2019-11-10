package com.xianyue.design.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AggregateData {

    public static AggregateData getInstance() {
        log.info("aggregate get instance");
        return AggregatePrivateConstruct.getInstance();
    }

    AggregateData() {
        log.info("aggregate construct after getInstance");
    }

    private static class AggregatePrivateConstruct {
        private AggregatePrivateConstruct() {
            log.info("aggregate private construct, but not execute");
        }

        private static AggregateData aggregateData = new AggregateData();

        static AggregateData getInstance() {
            return aggregateData;
        }

    }
}
