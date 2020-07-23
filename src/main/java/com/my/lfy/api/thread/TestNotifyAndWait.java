package com.my.lfy.api.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TestNotifyAndWait
 * 模拟队列
 *
 * @author lfy
 * @date 2020/7/23
 **/
public class TestNotifyAndWait {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 50, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
                , new ThreadFactoryBuilder().setNameFormat("线程通信测试--%d").build());
        TaskQueue taskQueue = new TaskQueue();

//        executor.execute(() -> {
//            while (true) {
//                String s = taskQueue.getTask();
//                System.out.println("execute task : " + s);
//            }
//        });
//
//        executor.execute(() -> {
//            for (int i = 0; i < 10; i++) {
//                String s = "t-" + Math.random();
//                System.out.println("add task : " + s);
//                taskQueue.addTask(s);
//            }
//        });

        ThreadA a = new ThreadA();
        a.start();
        ThreadB b = new ThreadB();
        b.start();
    }
}

class TaskQueue {

    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public synchronized String getTask() {
        while (queue.isEmpty()) {
            try {
                //当前线程进度等待状态,并释放锁
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //获取并移除此队列的头
        return queue.remove();
    }

}

class Monitor {
    public static final Object LOCK = new Object();
}

class ThreadA extends Thread {
    @Override
    public void run() {
        synchronized (Monitor.LOCK) {
            for (int i = 1; i <= 10; i++) {
                System.out.println("ThreadA--------------run," + i);
                if (i == 5) {
                    try {
                        Monitor.LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class ThreadB extends Thread {
    @Override
    public void run() {
        synchronized (Monitor.LOCK) {
            System.out.println("ThreadB-------------run");
            Monitor.LOCK.notifyAll();
        }
    }
}