package com.xianyue.mail.collector.memory;

import com.xianyue.mail.collector.ICollector;
import com.xianyue.mail.handler.IHandler;
import com.xianyue.mail.sender.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Xianyue
 */
public class MemoryCollector implements ICollector {

    private static final Logger               logger = LoggerFactory.getLogger(MemoryCollector.class);
    private ConcurrentLinkedQueue<MailEntity> queue  = new ConcurrentLinkedQueue<>();

    public boolean addMessage(MailEntity entity) {
        if (null == entity) {
            logger.error("MailEntity is null.");
            return false;
        }
        return this.queue.add(entity);
    }

    // 获取队列中元素
    private List<MailEntity> polls() {
        List<MailEntity> mails = new ArrayList<>();
        MailEntity mail = queue.poll();
        while (mail != null) {
            mails.add(mail);
            mail = queue.poll(); // if the queue is empty, poll() will return null,
        }
        return mails;
    }

    /**
     * 将数据的处理放置到runnable中进行处理
     */
    @Override
    public List<Runnable> collectors(IHandler handler) {
        Runnable r = () -> {
            handler.handle(polls());
        };
        return Arrays.asList(r);
    }
}
