package com.xianyue.mail.sender;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xianyue.mail.util.PropertiesUtils;

import java.util.Properties;

/**
 * @author Xianyue
 */
public class MailFactory {
    public static ISender senders(JSONObject jsonObject) {
        JSONArray senders = jsonObject.getJSONArray("senders");
        if (senders.isEmpty()) {
            throw new RuntimeException("invaild config file. senders must be more than 0");
        }

        JSONObject senderConfig = null;
        for (int i = 0; i < senders.size(); i++) {
            senderConfig = senders.getJSONObject(i);
            ISender sender = createOneSender(senderConfig);
        }

        return null;
    }

    private static ISender createOneSender(JSONObject config) {
        String className = config.getString("class");
        try {
            Class<? extends ISender> clazz = (Class<? extends ISender>) Class.forName(className);
            ISender sender = clazz.newInstance();
            Properties prop = PropertiesUtils.json2Properties(null,config.getString("config"));
            sender.init(prop);
            return sender;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
