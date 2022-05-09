package com.my.lfy.api.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Test001
 *
 * @author lfy
 * @date 2020/5/22
 **/
public class Test001 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Thread d = new ThreadD();
        d.setPriority(1);
        Thread a = new Thread(new RunnableA());
        Thread c = new Thread(new RunnableB());
        d.start();
        a.start();
        c.start();
        FutureTask futureTask = new FutureTask(new CallableC());
        new Thread(futureTask).start();
        System.out.println("futureTask: " + futureTask.get());

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