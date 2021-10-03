package com.xianyue.basictype.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import java.util.Date;
import java.util.TimeZone;

public class SimpleTimeExample {

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
        if (today.equals(localDate)) {
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
        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);


        YearMonth yearMonth = YearMonth.now();
        System.out.printf("Days in month year: %s %d%n", yearMonth, yearMonth.lengthOfMonth());
        YearMonth creditCard = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("your credit expires on %s %n", creditCard);

        if (today.isLeapYear()) {
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
            LocalDate holiday = LocalDate.parse(goodFriday, formatter);
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

    public static void subTracingTowTimes() throws ParseException {
        TimeZone zone = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sf.setTimeZone(zone);
        String str3 = "1927-12-31 23:54:07";
        String str4 = "1927-12-31 23:54:08";
        Date sDt3 = sf.parse(str3);
        Date sDt4 = sf.parse(str4);
        long ld3 = sDt3.getTime() / 1000;
        long ld4 = sDt4.getTime() / 1000;
        System.out.println(ld4 - ld3);
    }

    public static void timeZoneOffset() {
        long startOf1900Utc = -2208988800000L;
        for (String id : TimeZone.getAvailableIDs()) {
            TimeZone zone = TimeZone.getTimeZone(id);
            if (zone.getRawOffset() != zone.getOffset(startOf1900Utc - 1)) {
                System.out.println(id);
            }
        }
    }

    public static void dateConvert() {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime localDateTime = LocalDateTime.parse("2019-07-31 00:00:00", dateTimeFormatter1);
        LocalDate localDate = LocalDate.parse("2019-07-31", dateTimeFormatter2);
        Date date = Date
            .from(LocalDateTime.parse("2019-07-31 00:00:00", dateTimeFormatter1).atZone(ZoneId.systemDefault()).toInstant());

        String strDateTime = "2019-07-31 00:00:00";
        String strDate = "2019-07-31";
        Long timestamp = 1564502400000l;

        /** LocalDateTime 转 LocalDate */
        System.out.println("LocalDateTime 转 LocalDate: " + localDateTime.toLocalDate());
        /** LocalDateTime 转 Long */
        System.out.println("LocalDateTime 转 Long: " + localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        /** LocalDateTime 转 Date */
        System.out.println("LocalDateTime 转 Date: " + Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        /** LocalDateTime 转 String */
        System.out.println("LocalDateTime 转 String: " + localDateTime.format(dateTimeFormatter1));

        System.out.println("-------------------------------");

        /** LocalDate 转 LocalDateTime */
        System.out.println("LocalDate 转 LocalDateTime: " + LocalDateTime.of(localDate, LocalTime.parse("00:00:00")));
        /** LocalDate 转 Long */
        System.out.println("LocalDate 转 Long: " + localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        /** LocalDate 转 Date */
        System.out.println("LocalDate 转 Date: " + Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        /** LocalDate 转 String */
        System.out.println("LocalDateTime 转 String: " + localDateTime.format(dateTimeFormatter2));

        System.out.println("-------------------------------");

        /** Date 转 LocalDateTime */
        System.out.println("Date 转 LocalDateTime: " + LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        /** Date 转 Long */
        System.out.println("Date 转 Long: " + date.getTime());
        /** Date 转 LocalDate */
        System.out
            .println("Date 转 LocalDateTime: " + LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate());
        /** Date 转 String */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        System.out.println("Date 转 String: " + sdf.format(date));

        System.out.println("-------------------------------");

        /** String 转 LocalDateTime */
        System.out.println("String 转 LocalDateTime: " + LocalDateTime.parse(strDateTime, dateTimeFormatter1));
        /** String 转 LocalDate */
        System.out.println("String 转 LocalDate: " + LocalDateTime.parse(strDateTime, dateTimeFormatter1).toLocalDate());
        System.out.println("String 转 LocalDate: " + LocalDate.parse(strDate, dateTimeFormatter2));
        /** String 转 Date */
        System.out.println("String 转 Date: " + Date
            .from(LocalDateTime.parse(strDateTime, dateTimeFormatter1).atZone(ZoneId.systemDefault()).toInstant()));

        System.out.println("-------------------------------");

        /** Long 转 LocalDateTime */
        System.out
            .println("Long 转 LocalDateTime:" + LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
        /** Long 转 LocalDate */
        System.out.println(
            "Long 转 LocalDate:" + LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate());

    }

    public static void main(String[] args) throws ParseException {
        getNow();

        getElement();

        subTracingTowTimes();

        timeZoneOffset();
    }
}
