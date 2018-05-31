package com.xianyue.date;

import lombok.experimental.UtilityClass;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@UtilityClass
public class DateUtil {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String getNowTimeText() {
        return formatter.format(LocalDateTime.now());
    }

    public long getStartOfDay() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(1);
        return Timestamp.valueOf(localDate.atStartOfDay()).getTime();
    }

    public String convertTime(long currentStamp) {
        return formatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(currentStamp), TimeZone.getDefault().toZoneId()));
    }

    public static void main(String[] args) {
        System.out.println(convertTime(System.currentTimeMillis()));
        System.out.println(getStartOfDay());
        System.out.println(getNowTimeText());
    }
}
