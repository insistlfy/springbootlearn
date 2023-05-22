//package com.my.lfy.config.schedule;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
///**
// * @FileName: ScheduleTask
// * @Description: 动态定时任务
// * @Author: lfy
// * @CreateDate: 2023/4/21 16:18
// * @Version: 1.0
// **/
//@Slf4j
//@Component
//@PropertySource("classpath:/task-config.ini")
//public class ScheduleTask implements SchedulingConfigurer {
//
//    @Value("${printTime.cron:1}")
//    private String cron;
//
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(() -> log.info("Current time： {}", LocalDateTime.now()), triggerContext -> {
//            // 使用CronTrigger触发器，可动态修改cron表达式来操作循环规则
//            CronTrigger cronTrigger = new CronTrigger(cron);
//            return cronTrigger.nextExecutionTime(triggerContext);
//        });
//    }
//}
