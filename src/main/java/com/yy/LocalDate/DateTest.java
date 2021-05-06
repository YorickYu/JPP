package com.yy.LocalDate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.TimeZone;

public class DateTest {

    public static void main(String[] args) throws InterruptedException {
        LocalDateTest();
        LocalTimeTest();
        localDateTimeTest();
        instantTest();
        durationAndPeriod();
    }

    /**
     * @Description LocalDate
     *
     * @Author yloopdaed
     * @Date 20:13 2021/4/29
     */
    private static void LocalDateTest() {
        LocalDate now = LocalDate.now(); // 2021-04-29

        LocalDate date = LocalDate.of(2021, 4, 29); // 2021-04-29
        int year = date.get(ChronoField.YEAR); // 2021
        int month = date.get(ChronoField.MONTH_OF_YEAR); // 4
        int day = date.get(ChronoField.DAY_OF_MONTH); // 29

        int dateYear = date.getYear(); // 2021
        Month month1 = date.getMonth(); // APRIL

        int dayOfMonth = date.getDayOfMonth(); // 29
        DayOfWeek dayOfWeek = date.getDayOfWeek(); // THURSDAY
        int dayOfYear = date.getDayOfYear(); // 119

        LocalDate datePr = LocalDate.parse("2014-03-18");

        // date 转 string
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20210429
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2021-04-29

        // string 转 date
        LocalDate date1 = LocalDate.parse("20210429",
                DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2021-04-29",
                DateTimeFormatter.ISO_LOCAL_DATE);

        // 按照某种形式获取date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date3 = LocalDate.parse(s1, formatter);
    }

    /**
     * @Description LocalTime
     *
     * @Author yloopdaed
     * @Date 10:21 2021/4/30
     */
    private static void LocalTimeTest() {
        LocalTime time = LocalTime.of(15, 45, 20);
        int hour = time.getHour(); // 15
        int minute = time.getMinute(); // 45
        int second = time.getSecond(); // 20

        LocalTime timePr = LocalTime.parse("12:45:20");

    }

    /**
     * @Description LocalDateTime
     *
     * 为了便于人阅读使用
     *
     * @Author yloopdaed
     * @Date 10:58 2021/4/30
     */
    private static void localDateTimeTest() {
        LocalDateTime dt1 = LocalDateTime.of(2021, Month.MARCH, 18, 13, 45, 20);
        System.out.println(dt1); // 2021-03-18T13:45:20

        // 返回一个 LocalDate
        LocalDate date = dt1.toLocalDate();
        // 返回一个 LocalTime
        LocalTime time = dt1.toLocalTime();
    }

    /**
     * @Description Instant
     *
     * 以纳秒为单位
     * Instant的设计初衷是为了便于机器使用。
     *
     * @Author yloopdaed
     * @Date 10:56 2021/4/30
     */
    private static void instantTest() {
        Instant instant = Instant.ofEpochSecond(0); // 1970-01-01T00:00:00Z
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000);
        Instant.ofEpochSecond(4, -1_000_000_000);

        Instant nowInstant = Instant.now(); // 2021-04-30T02:54:14.051Z

        System.out.println(instant);
    }


    /**
     * @Description Duration & Period
     *
     * @Author yloopdaed
     * @Date 12:57 2021/4/30
     */
    private static void durationAndPeriod() throws InterruptedException {

        // 处理时间间隔 Duration
        LocalTime time1 = LocalTime.now();
        spare();
        LocalTime time2 = LocalTime.now();
        Duration d1 = Duration.between(time1, time2);

        LocalDateTime dateTime1 = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime dateTime2 = LocalDateTime.now();
        Duration d2 = Duration.between(dateTime1, dateTime2);

        Instant instant1 = Instant.now(); // 2021-04-30T02:54:14.051Z
        Instant instant2 = Instant.now(); // 2021-04-30T02:54:14.051Z
        Duration d3 = Duration.between(instant1, instant2);

        System.out.println(d1.toMillis());
        System.out.println(d2.toMillis());
        System.out.println(d3.toMillis());

        // 处理日期间隔
        Period tenDays = Period.between(LocalDate.of(2021, 3, 8),
                LocalDate.of(2021, 3, 18));
        System.out.println(tenDays.getDays());


        // 其他方式
        Duration threeMinutes1 = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        Period twentyDays = Period.ofDays(20);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    }

    private static void spare() {
        int sum = 0;
        for (int i = 0; i < 100_000_00; i++) {
            sum += i;
        }
    }

    /**
     * @Description TimeZone
     *
     * @Author yloopdaed
     * @Date 12:57 2021/4/30
     */
    private static void timeZoneTest() {
        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
    }
}
