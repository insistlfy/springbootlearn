package com.my.lfy.api.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test002
 *
 * @author lfy
 * @date 2020/6/29
 **/
public class Test002 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        List<List<String>> lists = Collections.singletonList(list);
        List<String> list2 = Collections.singletonList("2");


        System.out.println(lists);
        System.out.println(list2);
    }
}
