package com.my.lfy.api.thread;

import java.util.concurrent.*;

/**
 * @ClassName: TestCyclicBarrier
 * @Description: 所有线程在其他线程没有准备好之前都在被阻塞中, 等到所有线程都准备好了才继续执行
 * @Author: LFY
 * @Created: 2021/8/26 10:48
 * @Versions: V1.0
 */
public class TestCyclicBarrier {

    private static final Integer COUNT = 10;

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(10, 50, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //模拟场景 : 就用已经被说烂的跑步场景吧,十名运动员各自准备比赛,需要等待所有运动员都准备好以后,裁判才能说开始然后所有运动员一起跑;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("所有人都准备好了裁判开始了"));

        for (int i = 0; i < COUNT; i++) {

            final int times = i;
            executorService.execute(() -> {
                System.out.println("子线程" + times + "正在准备");
                try {
                    Thread.sleep(1000 * times);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + times + "准备好了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + times + "开始跑了");
            });

        }

    }
}
