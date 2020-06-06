package com.my.lfy.api.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * TestSynchronizedAndLock
 * <p>
 * Lock-------->ReentrantLock(可重入锁)
 * ReadWriteLock ------> ReentrantReadWriteLock(读写锁)
 *
 * @author lfy
 * @date 2020/6/4
 **/
public class TestSynchronizedAndLock {

    public static void main(String[] args) {

        synchronized (new Object()) {

        }

        new ReentrantLock();
    }
}
