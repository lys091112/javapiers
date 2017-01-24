package com.xianyue.mail.collector;

import com.xianyue.mail.collector.kafka.KafkaConsumer;
import com.xianyue.mail.collector.memory.MemoryCollector;
import com.xianyue.mail.constants.CollectorType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xianyue
 */
public class CollectorFactory {
    private static final Logger logger = LoggerFactory.getLogger(CollectorFactory.class);

    public static ICollector createCollector(String type) {
        if (isInvaild(type)) {
            throw new RuntimeException("invalid config file. type is " + type);
        }

        logger.info("create collector. collector type is {}", type);
        CollectorType enumType = CollectorType.collectorType(type);
        switch (enumType) {
            case KAFKA:
                return new KafkaConsumer();
            case MEMORY:
                return new MemoryCollector();
        }

        logger.error("this must be incredible. type is {}", type);
        return null;
    }

    //是否合法
    private static boolean isInvaild(String type) {
        return StringUtils.isEmpty(type) || !CollectorType.contains(type);
    }
}
