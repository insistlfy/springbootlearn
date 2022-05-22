package com.my.lfy.api.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @FileName: TestVolatile
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/20 14:59
 **/
@Slf4j
public class TestVolatile {


//    private static ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
//            new SynchronousQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private static Boolean FLAG = true;

    public static void main(String[] args) throws InterruptedException {
        // 单例线程池,这种线程池只有一个线程，且内部的线程会按照加入的顺序执行
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(() -> {
            FLAG = false;
            System.out.println("111111");
        });
        executor.submit(() -> log.info("falg:{}", FLAG));
        executor.shutdown();


    }
}
