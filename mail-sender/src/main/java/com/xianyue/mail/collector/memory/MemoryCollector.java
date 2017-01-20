package com.xianyue.mail.collector.memory;

import com.xianyue.mail.collector.IMailCollector;
import com.xianyue.mail.sender.entity.MailEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Xianyue
 */
public class MemoryCollector implements IMailCollector {

    private static final Logger               logger = LoggerFactory.getLogger(MemoryCollector.class);
    private ConcurrentLinkedQueue<MailEntity> queue  = new ConcurrentLinkedQueue<>();

    public boolean addMessage(MailEntity entity) {
        if (entity == null) {
            logger.error("MailEntity is null.");
            return false;
        }
        return this.queue.add(entity);
    }

    //获取队列中元素
    private List<MailEntity> polls() {
        List<MailEntity> mails = new ArrayList<>();
        MailEntity mail = queue.poll();
        while (mail != null) {
            mails.add(mail);
            mail = queue.poll(); // if the queue is empty, poll() will return null,
        }
        return mails;
    }

    @Override
    public int coresNumber() {
        return 1;
    }

    @Override
    public List<MailEntity> fetchMails() {
        return polls();
    }
}
