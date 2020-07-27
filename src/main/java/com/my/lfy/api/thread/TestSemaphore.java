package com.my.lfy.api.thread;

import java.util.concurrent.Semaphore;

/**
 * TestSemaphore
 * 题目 : 三个线程a、b、c并发运行，b,c需要a线程的数据怎么实现
 *
 * @author lfy
 * @date 2020/7/27
 **/
public class TestSemaphore {

    private static int NUM;
    private static Semaphore SEMAPHORE = new Semaphore(1);

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            NUM = 1;
            System.out.println(Thread.currentThread().getName() + "初始化NUM,NUM = " + NUM);
            //释放一个许可，将其返回给信号量
            try {
                SEMAPHORE.acquire();
            } catch (InterruptedException e) {
                SEMAPHORE.release();
            }
            SEMAPHORE.release();
        }, "threadA");

        Thread threadB = new Thread(() -> {
            try {
                //从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断
                SEMAPHORE.acquire();
                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",NUM = " + NUM);
        }, "threadB");

        Thread threadC = new Thread(() -> {
            try {
                SEMAPHORE.acquire();
                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ",NUM = " + NUM);
        }, "threadC");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
