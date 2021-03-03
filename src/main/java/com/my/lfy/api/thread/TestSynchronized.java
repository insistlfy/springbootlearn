package com.my.lfy.api.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

/**
 * TestSynchronized
 * 参考 ： https://www.cnblogs.com/theRhyme/p/10078402.html
 * 学习链接 ：https://www.toutiao.com/i6934309527080829443/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1614603210&app=news_article&utm_source=weixin&utm_medium=toutiao_android&use_new_style=1&req_id=202103012053300101351650891E090AF0&share_token=e08d7f93-57f0-4bb8-814c-e0d8c2d52009&group_id=6934309527080829443
 *
 * @author lfy
 * @date 2020/8/9
 **/
@Slf4j
public class TestSynchronized {

    private static volatile boolean flag = true;

    private static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

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


        /**
         * 验证可重入
         */
        Runnable sellTicket = () -> {
            synchronized (TestSynchronized.class) {
                log.info("this is run");
                test01();
            }
        };
        new Thread(sellTicket).start();


        /**
         * 验证不可中断性
         */
        Runnable run = () -> {
            synchronized (obj) {
                log.info("{}-进入代码块.", Thread.currentThread().getName());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    log.error("e------>", e);
                }
            }
        };

        Thread t1 = new Thread(run);
        t1.start();
        Thread.sleep(1000);

        Thread t2 = new Thread(run);
        t2.start();

        log.info("停止线程前：");
        t2.interrupt();
        log.info("停止线程后。");
        log.info("t1 state is {}.", t1.getState());
        log.info("t2 state is {}.", t2.getState());
    }

    public static void test01() {
        synchronized (TestSynchronized.class) {
            log.info("this is test01");
        }
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
