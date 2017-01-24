package com.xianyue.mail.sender;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xianyue.mail.util.PropertiesUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 * @author Xianyue
 */
public class MailFactory {
    private static Logger logger = LoggerFactory.getLogger(MailFactory.class);

    public static Map<String, ISender> senders(JSONArray senders) {
        if (senders.isEmpty()) {
            throw new RuntimeException("invaild config file. senders must be more than 0");
        }

        JSONObject senderConfig;
        String senderName;
        Optional<ISender> sender;
        Map<String, ISender> result = new HashMap<>();
        for (int i = 0; i < senders.size(); i++) {
            senderConfig = senders.getJSONObject(i);
            senderName = senderConfig.getString("name");
            if (StringUtils.isEmpty(senderName)) {
                logger.error("invaild config file. sender name is null, so this sender will be ignored!");
                continue;
            }
            sender = createOneSender(senderName, senderConfig);
            if (!sender.isPresent()) {
                continue;
            }
            result.put(senderName, sender.get());
        }

        return result;
    }

    /**
     * 根据配置文件，创建一个sender
     */
    private static Optional<ISender> createOneSender(String senderName, JSONObject config) {
        String className = config.getString("class");
        try {
            Class<? extends ISender> clazz = (Class<? extends ISender>) Class.forName(className);
            ISender sender = clazz.newInstance();
            Properties prop = PropertiesUtils.json2Properties(null, config.getString("config"));
            sender.init(prop);
            return Optional.of(sender);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            logger.error("initial sender error. senderName is {},className is {}. {}", senderName, className, e);
            return Optional.empty();
        }
    }
}
