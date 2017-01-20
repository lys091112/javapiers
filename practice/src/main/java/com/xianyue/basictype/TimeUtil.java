package com.xianyue.basictype;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class TimeUtil {

    /**
     * – Instant——它代表的是时间戳 – LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。 –
     * LocalTime——它代表的是不含日期的时间 – LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。 –
     * ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的
     */
    public static void getNow() {
        Instant instant = Instant.now();
        System.out.format("instant %s\n", instant);
        LocalDate localDate = LocalDate.now();
        System.out.format("localData %s\n", localDate);
        LocalTime localTime = LocalTime.now();
        System.out.format("localTime %s\n", localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.format("localDataTime %s\n", localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.format("zonedDateTime %s\n", zonedDateTime);
    }

    public static void getElement() {
        LocalDate today = LocalDate.now();
        System.out.format("Today Year:%s Month:%d Day:%d \t %n", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        
        //获取特定日期
        LocalDate dateOfBirth = LocalDate.of(1991, 2, 7);
        System.out.println("My birth Day is :" + dateOfBirth);
        
        LocalDate localDate = LocalDate.of(2016, 8, 2);
        if(today.equals(localDate)) {
            System.out.println("is same day");
        }
        
        LocalTime time = LocalTime.now();
        System.out.println("after 2 hours :" + time.plusHours(4));
        
        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println("next week day:" + nextWeek);
        
        LocalDate perviousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("last year :" + perviousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("next year " + nextYear);
        
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);
        
        System.out.println("--- " + Clock.systemDefaultZone());
        System.out.println("Clock : " + clock);
        
        
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZoneId america = ZoneId.of("America/New_York");
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

        
        YearMonth yearMonth = YearMonth.now();
        System.out.printf("Days in month year: %s %d%n", yearMonth, yearMonth.lengthOfMonth());
        YearMonth creditCard = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("your credit expires on %s %n", creditCard);
        
        if(today.isLeapYear()) {
            System.out.println("this year is leap year");
        }
        
        LocalDate release = LocalDate.of(2018, Month.MARCH, 14);
        Period period = Period.between(today, release);
        System.out.println("years left between today and release:" + period.getYears());
        System.out.println("month left between today and release:" + period.getMonths());
        System.out.println("days left between today and release:" + period.getDays());
        
        
        String str = "20180207";
        LocalDate formatted = LocalDate.parse(str, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("formatted date is :" + formatted);
        
        String goodFriday = "八月 18 2014";
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate holiday = LocalDate.parse(goodFriday,formatter);
            System.out.printf("prase string %s = %s %n ", goodFriday, holiday);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        
        LocalDateTime arrivalDate = LocalDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        String landding = arrivalDate.format(formatter2);
        System.out.printf("arrival date is %S %n", landding);
        
        System.out.println("current " + Instant.now().getNano());
        System.out.println("current " + Instant.now().getEpochSecond());
        System.out.println("current " + System.currentTimeMillis());
    }
    
    public static void main(String[] args) {
        getNow();
        
        getElement();
        
    }


}
