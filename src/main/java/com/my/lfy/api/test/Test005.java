package com.my.lfy.api.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Administrator
 */
public class Test005 {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
