package com.xianyue.file;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Xianyue 配置文件加载
 */
public class ConfigurationLoader {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    public static JSONObject loadJsonConfiguration(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            reader.lines().forEach(sb::append);
            return JSONObject.parseObject(sb.toString());
        } catch (FileNotFoundException e) {
            logger.error("invaild file path. path: {},error: {}", path, e.getMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("close reader error. {}", e);
                }
            }
        }
    }
}
