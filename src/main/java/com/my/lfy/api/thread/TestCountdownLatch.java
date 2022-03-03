package com.my.lfy.api.thread;

import java.util.concurrent.*;

/**
 * @ClassName: TestCountdownLatch
 * @Description: 顾名思义CountdownLatch可以当做一个计数器来使用,
 * 比如某线程需要等待其他几个线程都执行过某个时间节点后才能继续执行 。
 * @Author: LFY
 * @Created: 2021/8/26 10:47
 * @Versions: V1.0
 */
public class TestCountdownLatch {

    private static final Integer COUNT = 10;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //模拟场景：某公司一共有十个人,门卫要等十个人都来上班以后,才可以休息
        final CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < COUNT; i++) {
            final int times = i;
            executorService.execute(() -> {
                System.out.println("员工" + times + "正在赶路");
                try {
                    Thread.sleep(1000 * times);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("员工" + times + "到公司了");
                //调用latch的countDown方法使计数器-1
                latch.countDown();
                System.out.println("员工" + times + "开始工作");
            });
        }

        System.out.println("门卫等待员工上班中...");
        //主线程阻塞等待计数器归零
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("员工都来了,门卫去休息了");
        //线程池有两种关闭的方法，第一种是调用shutdown 方法，shutdown方法并不会立即停止所有的任务，
        // shutdown方法会拒绝新任务，但是已经发送到线程池中的任务会执行，当所有的任务执行完成，线程池才会关闭线程.
        // 这种关闭方式并不会立即停止线程池，但对于应用程序而言所有的任务都已经执行完成，不会出现一些安全性问题，
        // 比如说一些事务已经在线程池中提交，由于shutdown方法并不会中断或者取消已经在线程池中的任务，
        // 但是另一种方式shutdownNow就不太一样，它不仅会停止新任务的提交，
        // 它还会尝试取消或者中断正在执行的任务，如果这个时候有些事务性的操作已经完成，但它又被中断了，
        // 所以会产生一些安全性问题，所以建议大家如果提交的任务不在意结果，则可以调用shutdownNow,
        // 如果在意还是用shutdown方式比较好。
//        executorService.shutdown();
        Thread.sleep(1000);
        //打印线程池状态
        System.out.println("线程池状态：" + executorService.isTerminated());

        executorService.execute(() -> System.out.println("线程池还可以使用吗？"));
    }
}
