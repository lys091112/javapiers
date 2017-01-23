package com.xianyue.mail;

import ch.qos.logback.core.joran.spi.JoranException;
import com.alibaba.fastjson.JSONObject;
import com.xianyue.mail.collector.IMailCollector;
import com.xianyue.mail.constants.MailConstants;
import com.xianyue.mail.sender.ISender;
import com.xianyue.mail.sender.MailFactory;
import com.xianyue.mail.sender.entity.MailEntity;
import com.xianyue.mail.util.ConfigurationLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
    private Map<String, ISender>     mailSenders;
    private IMailCollector           mailCollector;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

    // 根据不同的收集类型采用不同的数据收集器
    public MailWorker(String collectotType) {

    }

    public void setMailCollector(IMailCollector mailCollector) {
        this.mailCollector = mailCollector;
    }

    /**
     * 加载配置文件，初始化worker
     */
    public void initialize() throws IOException, JoranException {
        LogBackConfigLoader.load("conf/logback.xml");
        Optional<JSONObject> senderConfig = ConfigurationLoader.loadJsonConfiguration("sender.json");
        if(!senderConfig.isPresent()) {
            throw new RuntimeException("缺少对应的配置文件. conf/sender.json");
        }
        MailFactory.senders(senderConfig.get());

    }

    public void start() throws IOException, JoranException {
        initialize();

        int cores = mailCollector.coresNumber();
        if (cores <= 0) {
            logger.warn("Not specified Cores count. default is 1");
            cores = 1;
        }

        for (int i = 0; i < cores; i++) {
            executorService.schedule(this::process, 3, TimeUnit.SECONDS);
        }
    }

    private void process() {
        List<MailEntity> mails = mailCollector.fetchMails();
        Optional<ISender> sender;
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
    private Optional<ISender> getMailSender(String template) {
        if (StringUtils.isEmpty(template)) {
            ISender sender = mailSenders.get(MailConstants.DEFAULT);
            if (sender == null) {
                throw new IllegalArgumentException("there must be a default mailsender");
            }
            return Optional.of(sender);
        }
        return Optional.of(mailSenders.get(template));
    }


}
