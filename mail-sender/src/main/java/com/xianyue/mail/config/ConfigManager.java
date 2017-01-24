package com.xianyue.mail.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Xianyue
 */
public class ConfigManager {
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    private ConfigManager() {

    }

    private static ConfigManager configManager = new ConfigManager();

    public static ConfigManager getInstance() {
        return configManager;
    }

    private AtomicReference<ConfigSender> configSender;

    /**
     * 保证该值只会被设置一次
     */
    public boolean setConfigSender(String configJson) {
        ConfigSender configSender = JSON.parseObject(configJson, ConfigSender.class);
        boolean res = this.configSender.compareAndSet(null, configSender);
        if (!res) {
            logger.warn("this config has been initialized!");
        }
        return res;
    }

    public String getCollector() {
        return configSender.get().getCollector();
    }

    public String getDefaultSenderName() {
        return configSender.get().getDefaultSender();
    }

    public String getHandler() {
        return configSender.get().getHandler();
    }

    public JSONArray getSenders() {
        return JSONArray.parseArray(configSender.get().getSenders());
    }

}
