package com.my.lfy.api.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * TestParalleStream
 * 测试使用及使用场景
 *
 * @author lfy
 * @CreateDate: 2020/8/6
 **/
public class TestParalleStream {


    private static final List<Integer> LIST_1 = new ArrayList<>();
    private static final List<Integer> LIST_2 = new ArrayList<>();
    private static final List<Integer> LIST_3 = new ArrayList<>();
    private static final List<Integer> LIST_4 = new ArrayList<>();
    private static final Lock LOCK = new ReentrantLock();
    private static final Object OBJ = new Object();


    public static void main(String[] args) {

        IntStream.range(0, 10000).forEach(LIST_1::add);
        IntStream.range(0, 10000).parallel().forEach(LIST_2::add);
        IntStream.range(0, 10000).parallel().forEach(e -> {
            synchronized (OBJ) {
                LIST_4.add(e);
            }
        });
        IntStream.range(0, 10000).parallel().forEach(e -> {
            LOCK.lock();
            try {
                LIST_3.add(e);
            } finally {
                LOCK.unlock();
            }
        });

        System.out.println("串行执行的大小：" + LIST_1.size());
        System.out.println("并行执行的大小：" + LIST_2.size());
        System.out.println("加锁并行执行的大小1：" + LIST_3.size());
        System.out.println("加锁并行执行的大小2：" + LIST_4.size());
    }
}
