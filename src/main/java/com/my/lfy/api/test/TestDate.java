package com.my.lfy.api.test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * TestDate
 *
 * @author lfy
 * @date 2021/4/13
 **/
public class TestDate {

    public static void main(String[] args) {

        LocalDate startDate = LocalDate.of(2017, Month.APRIL, 7);
        System.out.println("startDate : " + startDate);

        LocalDate loveDate = LocalDate.of(2017, Month.SEPTEMBER, 10);
        System.out.println("loveDate : " + loveDate);

        LocalDate endDate = LocalDate.of(2021, Month.APRIL, 16);
        System.out.println("endDate : " + endDate);

        Period period = Period.between(startDate, endDate);
        Period lovePeriod = Period.between(loveDate, endDate);

        Long days = ChronoUnit.DAYS.between(startDate, endDate);
        Long loveDays = ChronoUnit.DAYS.between(loveDate, endDate);

        System.out.printf("相识 ： %d年 %d月 %d日", period.getYears(), period.getMonths(), period.getDays());
        System.out.println();
        System.out.printf("相识总天数 ： %d天", days);
        System.out.println();
        System.out.println("==================================================");
        System.out.printf("相恋 ： %d年 %d月 %d日", lovePeriod.getYears(), lovePeriod.getMonths(), lovePeriod.getDays());
        System.out.println();
        System.out.printf("相恋总天数 ：%d天", loveDays);

        System.out.println("==================================================");
        System.out.println("相识第一站 ：大雁搭");
        System.out.println("相识前一站 ：小雁搭");
        System.out.println("==================================================");

        LocalDate testDate = LocalDate.parse("2021-04-13");
        LocalDate testDate1 = LocalDate.parse("2021-04-13", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(testDate);
        System.out.println(testDate1);

    }
}
