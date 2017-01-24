package com.xianyue.mail.collector.kafka;

import com.xianyue.mail.collector.ICollector;
import com.xianyue.mail.handler.IHandler;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Xianyue
 */
public class KafkaConsumer implements ICollector {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);


    private Properties          config;



    public void start() {
        String topicName = config.getProperty("topic");
        Integer topicThreadNum = Integer.valueOf(config.getProperty("topic.thread.num"));

        ConsumerConfig consumerConfig = new ConsumerConfig(config);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put(topicName, topicThreadNum);

        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, List<KafkaStream<String, String>>> topicMessageStreams = consumerConnector.createMessageStreams(map, new StringDecoder(null), new StringDecoder(null));
        final List<KafkaStream<String, String>> streams = topicMessageStreams.get(topicName);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(topicThreadNum);

        for (final KafkaStream<String, String> stream : streams) {
            executor.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    scheduleConsumer(stream);
                }
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    private void scheduleConsumer(KafkaStream<String, String> stream) {
        try {
            Iterator<MessageAndMetadata<String, String>> it = stream.iterator();
            while (it.hasNext()) {
                MessageAndMetadata<String, String> msgAndMetadata = it.next();
                if (msgAndMetadata == null || msgAndMetadata.message() == null || msgAndMetadata.message().trim().equals("")) {
                    continue;
                }
            }
        } catch (Throwable e) { // NOSONAR
            if (logger.isErrorEnabled()) {
                logger.error("kafka 消费线程错误", e);
            }
        }
    }

    @Override
    public List<Runnable> collectors(IHandler handler) {
        return null;
    }
}
