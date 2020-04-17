package com.my.lfy.api.springtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Task
 * 没有配置自己的线程池时，会默认使用SimpleAsyncTaskExecutor
 * 如果项目中只配置了一个线程池，那么不需要显示指定使用这个线程池，
 * spring也会自动使用用户配置的线程池，但是如果配置了多个就必须要显示指定，否则还是会使用默认的
 * 如果想要指定使用哪个线程池，可以使用@Async("executor2")显示指定
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
