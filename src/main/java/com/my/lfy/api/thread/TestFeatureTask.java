package com.my.lfy.api.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: TestFeatureTask
 * @Description: FeatureTask 案例
 * @Author: Lify
 * @CreateDate: 2022/5/5 16:26
 * @Version: 1.0
 **/
public class TestFeatureTask {

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {


//        Callable
//        Runnable
    }
}
