package com.johnny.jdk8.dateapi;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;

/**
 * Created by johnny01.yang on 2016/7/19.
 */
public class DateApiTest {

    /**
     * Instant | Duration （distance between two instant）
     */
    public static void test1(){
        // Instant represent a instant time
        Instant instant1 = Instant.now();
        for (int i=0;i<100;i++){
            System.out.println(i);
        }
        Instant instant2 = Instant.now();

        // Duration
        Duration duration = Duration.between(instant1, instant2);
        // evaluate whether two duration is ten times than other
        duration.multipliedBy(10).minus(Duration.ZERO).isNegative();

        // *-1
        duration.negated();
    }

    /**
     * 新的Java API中，有2中人类时间：本地日期/时间 和 带时区的日期/时间
     * API设计者推荐使用不带时区的时间原因：当跨越夏令时时，导致时间错误
     *
     * LocalDateTime(LocalDate) | ZonedDateTime(ZonedDate)
     *
     * LocalTime 时间
     *
     * Period(distance between two LocalDate(Time))
     */
    public static void test2(){
        LocalDate localDate = LocalDate.now();
        localDate = LocalDate.of(1991, 8, 19);
        Period period = localDate.until(LocalDate.of(1992,8,19));
        localDate.until(LocalDate.of(1992,8,19));

        // 2014年第256天
        LocalDate localDate1 = LocalDate.of(2014,1,1).plusDays(255);

        localDate1.getMonth();

        MonthDay monthDay = MonthDay.of(12, 3);
        YearMonth yearMonth = YearMonth.of(2015, 2);

        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(12, 12, 12, 200);
    }

    /**
     * TemporalAdjusters
     * TemporalAdjuster
     *
     */
    public static void test3(){
        LocalDate localDate = LocalDate.of(2015, 2, 3);
        LocalDate localDate1 = localDate.with(TemporalAdjusters.firstDayOfMonth());
        TemporalAdjuster temporalAdjuster = TemporalAdjusters.next(DayOfWeek.MONDAY);
        TemporalAdjuster temporalAdjuster1 = TemporalAdjusters.previous(DayOfWeek.MONDAY);
        System.out.println(localDate1.toString());
    }

    /**
     * ZonedDateTime 带时区的日期时间：同LocalDateTime
     */
    public static void test4(){
    }

    /**
     * format and/or parse java.time
     *
     * DateTimeFormatter
     */
    public static void test5(){
    }

    /**
     * interaction with dated code
     */
    public static void test6(){

    }

    public static void test7(){
    }



}
