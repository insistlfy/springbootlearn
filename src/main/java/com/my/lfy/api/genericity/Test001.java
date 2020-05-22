package com.my.lfy.api.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型简单学习
 *
 * @author lfy
 * @date 2020/5/22
 **/
public class Test001 {

    public static void main(String[] args) {

        List<Double> list = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("Hello World");
        List<Integer> list2 = new ArrayList<>();
        list2.add(255);

        Class c1 = list1.getClass();
        Class c2 = list2.getClass();
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(c1 == c2);
    }
}


