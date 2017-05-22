package com.xianyue.langfeature.java8;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 */
public class TimeFeature {

    public static void main(String[] args) {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        LocalDate localDate = LocalDate.now();
        LocalDate datefromClock = LocalDate.now(clock);
        System.out.println(localDate);
        System.out.println(datefromClock);

        LocalTime localTime = LocalTime.now();
        LocalTime timeFromClock = LocalTime.now(clock);
        System.out.println(localTime);
        System.out.println(timeFromClock);
    }
}
