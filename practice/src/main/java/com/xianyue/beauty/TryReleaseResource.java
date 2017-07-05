package com.xianyue.beauty;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Java 7 的编译器和运行环境支持新的 try-with-resources 语句，称为 ARM 块(Automatic Resource Management) ，自动资源管理
 * 被关闭的资源，需要实现AutoCloseable接口
 */
@Slf4j
public class TryReleaseResource {

    private static Map<Long, UserInfo> userInfoMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (UserInfo info = userInfoMap.get(1L)) {
            log.info("try release resource");
        } catch (Exception e) {
        }
        try (UserInfo info = userInfoMap.get(1L)) {
            log.info("double try release resource");
        } catch (Exception e) {
        }
        log.info("map info: {}", userInfoMap);

    }

    static {
        userInfoMap.put(1L, new UserInfo().setUserId(1L).setUserName("li").setHandleTimes(0));
    }


    @Data
    @Accessors(chain = true)
    public static class UserInfo implements Closeable {
        private long   userId;
        private String userName;
        private int    handleTimes;

        @Override
        public void close() throws IOException {
            log.warn("after handle");
            this.handleTimes++;
        }
    }
}
