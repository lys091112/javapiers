package com.xianyue.mail.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * @author Xianyue
 */
public class ConfigurationLoader {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    /**
     * load json file，将其转化为jsonObject对象
     */
    public static Optional<JSONObject> loadJsonConfiguration(String path) {
        try {
            StringBuilder sb = new StringBuilder();
            Files.lines(Paths.get(path), StandardCharsets.UTF_8).forEach(sb::append);
            return Optional.of(JSONObject.parseObject(sb.toString()));
        } catch (IOException e) {
            logger.error("load json config error.path={}, {}", path, e);
            return Optional.empty();
        }
    }
}
