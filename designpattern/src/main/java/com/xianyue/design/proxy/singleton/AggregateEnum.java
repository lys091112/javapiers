package com.xianyue.design.proxy.singleton;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class AggregateEnum {
    static AggregateEnum getInstance() {
        log.info("----getInstance-----------");
        return AggregateEnumFactory.AGGREGATE.instance();
    }

    private AggregateEnum() {
        log.info("aggregate enum construct");
    }


    private enum AggregateEnumFactory {
        AGGREGATE;
        private AggregateEnum aggregate;

        /**
         * JVM 保证只会被初始化一次
         */
        AggregateEnumFactory() {
            log.info("---- aggregate enum factory construct -------");
            aggregate = new AggregateEnum();
        }

        public AggregateEnum instance() {
            return aggregate;
        }

    }
}
