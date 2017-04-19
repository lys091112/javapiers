package com.xianyue.mail;

import ch.qos.logback.core.joran.spi.JoranException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xianyue.mail.collector.CollectorFactory;
import com.xianyue.mail.collector.ICollector;
import com.xianyue.mail.config.ConfigManager;
import com.xianyue.mail.config.ConfigSender;
import com.xianyue.mail.constants.MailConstants;
import com.xianyue.mail.handler.CommonHandler;
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
    private static final Logger logger = LoggerFactory.getLogger(MailWorker.class);
    private Map<String, ISender> mailSenders;
    private ICollector           mailCollector;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

    /**
     * 加载配置文件，初始化worker
     */
    public void initialize() throws IOException, JoranException {
        LogBackConfigLoader.load("conf/logback.xml");
        Optional<String> config = ConfigurationLoader.loadJsonConfiguration("sender.json");
        if (!config.isPresent()) {
            throw new RuntimeException("缺少对应的配置文件. conf/sender.json");
        }

        //初始化配置文件
        ConfigManager manager = ConfigManager.getInstance();
        manager.setConfigSender(config.get());

        //初始化sender
        this.mailSenders = MailFactory.senders(manager.getSenders());

        //初始化collector
        String collectorType = manager.getCollector();
        this.mailCollector = CollectorFactory.createCollector(collectorType);
    }

    /**
     * TODO 合理生成handler类
     */
    public void start() throws IOException, JoranException {
        initialize();

        List<Runnable> runnables = mailCollector.collectors(new CommonHandler(mailSenders));
        for (Runnable r : runnables) {
            executorService.schedule(r, 3, TimeUnit.SECONDS);
        }
    }

}
