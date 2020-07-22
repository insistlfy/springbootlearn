package com.my.lfy.api.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * Test003
 * 线程同步测试
 *
 * @author lfy
 * @date 2020/7/22
 **/
public class Test003 {

    public static void main(String[] args) {

        AddThread addThread = new AddThread();
        DecThread decThread = new DecThread();
        addThread.start();
        decThread.start();
        try {
            addThread.join();
            decThread.join();
            System.out.println(Counter.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Counter {
    public static final Object LOCK = new Object();
    public static int count = 0;
}

@Slf4j
class AddThread extends Thread {
    @Override
    public void run() {
        log.info("add ...");
        for (int i = 0; i < 10000; i++) {

            synchronized (Counter.LOCK) {
                Counter.count += 1;
            }
        }
    }
}

@Slf4j
class DecThread extends Thread {
    @Override
    public void run() {
        log.info("dec ...");
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.LOCK) {
                Counter.count -= 1;
            }
        }
    }
}