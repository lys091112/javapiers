package com.xianyue.date;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public String getNowTimeText() {
    return formatter.format(LocalDateTime.now());
  }

  public long getStartOfDay() {
    LocalDate localDate = LocalDate.now();
    localDate = localDate.plusDays(1);
    System.out.println(localDate);
    return Timestamp.valueOf(localDate.atStartOfDay()).getTime();
  }

  public static void main(String[] args) {
    System.out.println(getStartOfDay());
  }
}
