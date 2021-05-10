package com.my.lfy.api.test;

import java.util.*;

/**
 * Test004
 *
 * @author lfy
 * @date 2021/2/3
 **/
public class Test004 {

    private static final Long MAX_COUNT = 100L;

    //反例
    private static Map<String, Object> map = new HashMap<String, Object>() {
        {
            put("a", "a");
            put("b", "b");
        }
    };

    //正例
    private static Map<String, Object> newMap = new HashMap<>();

    static {
        newMap.put("a", "a");
        newMap.put("b", "b");
    }

    //反例
    public boolean isFinished(String orderStatus) {
        //存在空指针
        return orderStatus.equals("1");
    }

    //正例
    public boolean isSuccess(String orderStatus) {
        return "1".equals(orderStatus);
    }

    @Deprecated
    public void test() {

    }

    //反例
    public List<String> query() {
        return null;
    }

    //正例
    public List<String> query(String code) {
        return Collections.emptyList();
    }


    public static void main(String[] args) {

        //反例
        String str = "";
        for (int i = 0; i < 10; i++) {
            str += i;
        }

        //正例
        String a = "a";
        String b = "b";
        String c = "c";
        //java编译器自动优化
        String s = a + b + c;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(i);
        }

        String goodAt = "basketball.Swimming";
        String name = "James|Rose";

        //反例
//        System.out.println(goodAt.split(".")[0]);
//        System.out.println(name.split("|")[1]);

        //正例
        System.out.println(goodAt.split("\\.")[0]);
        System.out.println(name.split("|")[0]);
        System.out.println(name.split("\\|")[1]);
        System.out.println("===================================");

        String str1 = "ab" + "c";
        String str2 = "abc";
        System.out.println(str1 == str2);

        String str3 = "ab";
        String str4 = str3 + "c";
        System.out.println(str2 == str4);

        StringBuffer buffer = new StringBuffer();
    }

}
