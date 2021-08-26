package com.my.lfy.api.thread;

import java.util.concurrent.*;

/**
 * @ClassName: TestCountdownLatch
 * @Description: 顾名思义CountdownLatch可以当做一个计数器来使用,
 * 比如某线程需要等待其他几个线程都执行过某个时间节点后才能继续执行 。
 * @Author: LFY
 * @Created: 2021/8/26 10:47
 * @Versions: V3.0
 * @Company: ©2021东方微银科技（西安）有限公司
 */
public class TestCountdownLatch {

    private static final Integer COUNT = 10;

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //模拟场景：某公司一共有十个人,门卫要等十个人都来上班以后,才可以休息
        final CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < COUNT; i++) {
            final int times = i;
            executorService.execute(() -> {
                System.out.println("子线程" + times + "正在赶路");
                try {
                    Thread.sleep(1000 * times);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + times + "到公司了");
                //调用latch的countDown方法使计数器-1
                latch.countDown();
                System.out.println("子线程" + times + "开始工作");
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
    }
}
