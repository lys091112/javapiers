package com.xianyue.mail;

import com.xianyue.mail.collector.IMailCollector;
import com.xianyue.mail.sender.IMailSender;
import com.xianyue.mail.sender.entity.MailEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Xianyue
 */
public class MailWorker {
    private static final Logger      logger          = LoggerFactory.getLogger(MailWorker.class);
    private Map<String, IMailSender> mailSenders;
    private IMailCollector           mailCollector;

    ScheduledExecutorService         executorService = Executors.newScheduledThreadPool(4);

    // 根据不同的收集类型采用不同的数据收集器
    public MailWorker(String collectotType) {

    }

    public void setMailCollector(IMailCollector mailCollector) {
        this.mailCollector = mailCollector;
    }

    public void start() {
        int cores = mailCollector.coresNumber();
        if (cores <= 0) {
            logger.warn("cores is under zero. default is 1");
            cores = 1;
        }
        for (int i = 0; i < cores; i++) {
            executorService.schedule(this::process, 3, TimeUnit.SECONDS);
        }
    }

    private void process() {
        List<MailEntity> mails = mailCollector.fetchMails();
        Optional<IMailSender> sender;
        for (MailEntity entity : mails) {
            sender = getMailSender(entity.getTemplate());
            if (!sender.isPresent()) {
                logger.error("can't find a mailSender. template is {}", entity.getTemplate());
                continue;
            }
            sender.get().send(entity);
        }
    }

    /**
     * 如果不指定发送的template策略，那么使用default策略的sender发送
     */
    private Optional<IMailSender> getMailSender(String template) {
        if (StringUtils.isEmpty(template)) {
            IMailSender sender = mailSenders.get(MailConstants.DEFAULT);
            if (sender == null) {
                throw new IllegalArgumentException("there must be a default mailsender");
            }
            return Optional.of(sender);
        }
        return Optional.of(mailSenders.get(template));
    }


}
