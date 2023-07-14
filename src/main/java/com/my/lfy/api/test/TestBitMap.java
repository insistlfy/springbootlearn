package com.my.lfy.api.test;

import cn.hutool.bloomfilter.bitMap.IntMap;

/**
 * @FileName: TestBitMap
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/5/29 17:01
 * @Version: 1.0
 **/
public class TestBitMap {

    public static void main(String[] args) {
        IntMap intMap = new IntMap(1);
        System.out.println(intMap.contains(1));

        Object a = new Object();
        Object b = new Object();
        System.out.println(a.equals(b));
        System.out.println(a==b);
        System.out.println(a.hashCode()==b.hashCode());
        System.out.println("=====================================");

        User user1 = new User();
        user1.setAge(1);
        User user2 = new User();
        user2.setAge(1);
        System.out.println(user1==user2);
        System.out.println(user1.equals(user2));
        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user1.hashCode()==user2.hashCode());
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());

    }
}
