package com.my.lfy.api.springtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 如果线程池中线程数量 < 核心线程数，新建一个线程执行任务；
 * 如果线程池中线程数量 >= 核心线程数,则将任务放入任务队列
 * 如果线程池中线程数量 >= 核心线程数 且 < maxPoolSize，且任务队列满了，则创建新的线程；
 * 如果线程池中线程数量 > 核心线程数,当线程空闲时间超过了keepalive时，则会销毁线程；由此可见线程池的队列如果是无界队列，那么设置线程池最大数量是无效的；
 * 如果线程池中的任务队列满了，而且线程数达到了maxPoolSize，并且没有空闲的线程可以执行新的任务，这时候再提交任务就会执行拒绝策略
 *
 *
 * @author lfy
 * @date 19-12-8
 **/
@Slf4j
@Configuration
@EnableAsync
public class SpringTaskConfig {

    @Bean
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //线程池维护线程的最少数量，即使没有任务需要执行，也会一直存活
        executor.setCorePoolSize(10);
        //线程池维护线程的最大数量
        //当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
        //当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常
        executor.setMaxPoolSize(50);
        //允许的空闲时间，当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        executor.setKeepAliveSeconds(300);
        //缓存队列（阻塞队列）当核心线程数达到最大时，新任务会放在队列中排队等待执行
        executor.setQueueCapacity(50);
        //线程名称前缀
        executor.setThreadNamePrefix("BASIC-TASK-");
        //拒绝task的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //初始化
        executor.initialize();
        log.info("============================>BASIC-TASK starting....");
        return executor;
    }
}
