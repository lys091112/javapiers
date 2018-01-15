package com.xianyue.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Xianyue
 */
public class DatePrac {

    public static void main(String[] args) throws ParseException {
        timeZoneOffset();
        subTracingTowTimes();
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
}
