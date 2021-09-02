package com.my.lfy.api.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName: TestCompletableFuture
 * @Description: 某线程需要等待其他几个线程都执行过某个时间节点后才能继续执行 。
 * 1. supplyAsync表示创建带返回值的异步任务的，相当于ExecutorService submit(Callable<T> task) 方法，
 * runAsync表示创建无返回值的异步任务，相当于ExecutorService submit(Runnable task)方法，这两方法的效果跟submit是一样的;
 * 2.
 * @Author: LFY
 * @Created: 2021/8/31 10:18
 * @Versions: V3.0
 * @Company: ©2021东方微银科技（西安）有限公司
 */
public class TestCompletableFuture {

    private static final Integer COUNT = 10;

    ExecutorService executorService = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        testRunSync();

    }

    private static void testRunSync() {

        // 创建异步任务，无返回值
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println("test CompletableFuture runAsync...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("main thread start...");
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main thread end...");
    }

    private static String testSupplyAsync(ExecutorService executorService) {

        // 创建异步任务，有返回值
        List<String> result = new ArrayList<>();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            for (int i = 1; i <= COUNT; i++) {
                System.out.println(i + "准备好了！");
            }
            return null;
        }, executorService);
        return "";
    }
}
