package com.my.lfy.api.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * TestSynchronized
 * 参考 ： https://www.cnblogs.com/theRhyme/p/10078402.html
 *
 * @author lfy
 * @date 2020/8/9
 **/
@Slf4j
public class TestSynchronized {

    private static volatile boolean flag = true;

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run() {

                synchronized (this) {
                    if (flag) {
                        methodB();
                    } else {
                        methodA();
                    }
                }
            }
        }.start();

        log.info("{} : is-else-end.", Thread.currentThread().getName());
    }

    public static synchronized void methodA() {
        log.info("ThreadName : {}------methodA", Thread.currentThread().getName());
    }

    public static synchronized void methodB() {

        flag = false;
        log.warn("ThreadName : {}-----method will throw a exception.", Thread.currentThread().getName());

//        int a = 1 / 0;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("ThrandName : {}------methodB end.", Thread.currentThread().getName());

    }
}
