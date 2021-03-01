package com.my.lfy.api.test;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * TestParalleStream
 * 测试使用及使用场景
 *
 * @author lfy
 * @date 2020/8/6
 **/
public class TestParalleStream {


    private static List<Integer> list1 = new ArrayList<>();
    private static List<Integer> list2 = new ArrayList<>();
    private static List<Integer> list3 = new ArrayList<>();
    private static List<Integer> list4 = new ArrayList<>();
    private static Lock lock = new ReentrantLock();
    private static Object obj = new Object();


    public static void main(String[] args) {

        IntStream.range(0, 10000).forEach(list1::add);
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        IntStream.range(0, 10000).parallel().forEach(e -> {
            synchronized (obj) {
                list4.add(e);
            }
        });
        IntStream.range(0, 10000).parallel().forEach(e -> {
            lock.lock();
            try {
                list3.add(e);
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小1：" + list3.size());
        System.out.println("加锁并行执行的大小2：" + list4.size());

        Map<String, Object> hashMap = new HashMap<>(2);
        Map<String, Object> hashTable = new Hashtable<>();
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        Set<String> hashSet = new HashSet<>();
        Set<String> treeSet = new TreeSet<>();


    }
}
