package com.my.lfy.api.test;

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
            SEMAPHORE.release();
        }, "threadA");

        Thread threadB = new Thread(() -> {
            try {
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
