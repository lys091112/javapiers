package com.xianyue.mail.collector.kafka;

import com.xianyue.mail.collector.ICollector;
import com.xianyue.mail.handler.IHandler;

import java.util.List;

/**
 * @author Xianyue
 */
public class KafKaConsumer implements ICollector{
    @Override
    public List<Runnable> collectors(IHandler handler) {
        return null;
    }
}
