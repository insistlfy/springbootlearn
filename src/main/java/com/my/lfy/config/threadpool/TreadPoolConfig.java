package com.my.lfy.config.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * TreadPoolConfig
 *
 * @author lfy
 * @date 19-10-24
 **/
@Configuration
public class TreadPoolConfig {

    /**
     * 核心线程池大小
     */
    private static final Integer CORE_POOL_SIZE = 60;
    /**
     * 线程池最大线程数
     */
    private static final Integer MAXIMUM_POOL_SIZE = 100;
    /**
     * 线程没有任务执行时最多保持多久时间会终止
     */
    private static final Integer KEEP_ALINE_TIME = 30;


    @Bean
    public ExecutorService cardBindThreadPool() {
        //线程工场
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("card-bind-thread-%d").build();

        //线程池创建并返回
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALINE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), threadFactory, new ThreadPoolExecutor.DiscardPolicy());
    }
}
