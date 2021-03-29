package com.my.lfy.api.test;

import java.time.*;

/**
 * TestDurationAndPeriod
 *
 * @author lfy
 * @date 2021/3/29
 **/
public class TestDurationAndPeriod {

    public static void main(String[] args) {

        /*----------Period----------*/
        LocalDate startDate = LocalDate.of(2021, 2, 20);
        LocalDate endDate = LocalDate.of(2021, 3, 29);
        System.out.println(startDate);
        System.out.println(endDate);
        Period period = Period.between(startDate, endDate);
        System.out.println(period);
        System.out.println("-----------------------------------------------------");
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getUnits());
        System.out.println(period.isNegative());

        Period p2Y = Period.parse("P2Y");
        System.out.println(p2Y);
        System.out.println("-----------------------------------------------------");

        /*----------Duration----------*/
        Instant start = Instant.parse("2021-03-29T10:15:00.00Z");
        Instant end = Instant.parse("2021-03-29T10:15:01.00Z");

        Duration duration = Duration.between(start, end);
        System.out.println(duration.getSeconds());

        LocalTime start1 = LocalTime.of(1, 20, 25, 1024);
        LocalTime end1 = LocalTime.of(1, 22, 27, 1024);
        Duration duration1 = Duration.between(start1, end1);
        System.out.println(duration1.getSeconds());
        System.out.println(duration1.isNegative());


    }
}
