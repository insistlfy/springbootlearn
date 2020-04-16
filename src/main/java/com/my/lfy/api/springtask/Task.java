package com.my.lfy.api.springtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Task
 *
 * @author lfy
 * @date 2020/4/16
 **/
@Slf4j
@Configuration
@Async
public class Task {

    @Scheduled(cron = "${spring.task.test1}")
    public void test001() {
        System.out.println("I am test001...,threadName=" + Thread.currentThread().getName());
    }

    @Scheduled(cron = "${spring.task.test2}")
    public void test002() {
        System.out.println("I am test002...,threadName=" + Thread.currentThread().getName());
    }
}
