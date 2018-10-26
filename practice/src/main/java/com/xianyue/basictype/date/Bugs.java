package com.xianyue.basictype.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @since 下午2:58 18-9-19
 */
public class Bugs {

    public static void main(String[] args) {
        bugs_JDK8031085();
    }


    /**
     * 影响版本jdk1.8
     */
    private static void bugs_JDK8031085() {
        String x = "20130812214600025";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime t1 = LocalDateTime.parse(x, dtf);
        System.out.println(t1);
    }

}
