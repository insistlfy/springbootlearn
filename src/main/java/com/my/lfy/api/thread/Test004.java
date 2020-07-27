package com.my.lfy.api.thread;

/**
 * Test004
 * 题目 : 三个线程a、b、c并发运行，b,c需要a线程的数据怎么实现
 *
 * @author lfy
 * @date 2020/7/27
 **/
public class Test004 {

    public static void main(String[] args) {

        ThreadAA aa = new ThreadAA();
        ThreadBB bb = new ThreadBB();
        ThreadCC cc = new ThreadCC();

        bb.start();
        cc.start();
        aa.start();
    }
}

class Base {

    public static volatile int NUM = 1;

    public static final Object LOCK = new Object();
}


class ThreadAA extends Thread {

    @Override
    public void run() {

        synchronized (Base.LOCK) {
            Base.NUM = 10;
            System.out.println("ThreadAA starting ... ,num = " + Base.NUM);
            Base.LOCK.notifyAll();
        }
    }
}

class ThreadBB extends Thread {

    @Override
    public void run() {
        synchronized (Base.LOCK) {
            try {
                Base.LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadBB starting ...,num = " + Base.NUM);
        }
    }
}

class ThreadCC extends Thread {

    @Override
    public void run() {
        synchronized (Base.LOCK) {
            try {
                Base.LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadCC starting ... ,num = " + Base.NUM);
        }

    }
}