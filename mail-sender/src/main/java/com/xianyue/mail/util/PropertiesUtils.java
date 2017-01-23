package com.xianyue.mail.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author  Xianyue
 */
public class PropertiesUtils {

    public static Properties json2Properties(String scope, Object json) {
        Properties properties = new Properties();

        if (json instanceof JSONObject) {
            for (Map.Entry<String, Object> entry : ((JSONObject) json).entrySet()) {
                String key = scope == null ? entry.getKey() : scope + "." + entry.getKey();
                Object val = entry.getValue();
                if (val instanceof JSONObject) {
                    properties.putAll(json2Properties(key, val));
                } else if (val instanceof JSONArray) {
                    properties.put(key, jsonArray2List((JSONArray) val));
                } else {
                    properties.put(key, String.valueOf(val));
                }
            }
        }

        return properties;
    }

    private static List<Object> jsonArray2List(JSONArray array) {
        List<Object> list = new ArrayList<>();
        for (Object object : array) {
            if (object instanceof JSONArray) {
                list.add(jsonArray2List((JSONArray) object));
            } else if (object instanceof JSONObject) {
                list.add(json2Properties(null, object));
            } else {
                list.add(object);
            }
        }
        return list;
    }
}
