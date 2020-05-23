package com.my.lfy.api.thread;

import java.util.concurrent.Callable;

/**
 * Test001
 *
 * @author lfy
 * @date 2020/5/22
 **/
public class Test001 {

    public static void main(String[] args) {


        Thread d = new ThreadD();
        d.setPriority(1);
        Thread a = new Thread(new RunnableA());
        Thread c = new Thread(new RunnableB());
        d.start();
        a.start();
        c.start();
    }
}


class RunnableA implements Runnable {

    @Override
    public void run() {
        System.out.println("A===============>" + Thread.currentThread().getName());
    }
}

class RunnableB implements Runnable {

    @Override
    public void run() {
        System.out.println("B===============>" + Thread.currentThread().getName());
    }
}


class CallableC implements Callable {

    @Override
    public String call() throws Exception {
        System.out.println("C===============>" + Thread.currentThread().getName());
        return "Callable";
    }
}

class ThreadD extends Thread {

    @Override
    public void run() {
        System.out.println("D===============>" + Thread.currentThread().getName());
    }
}