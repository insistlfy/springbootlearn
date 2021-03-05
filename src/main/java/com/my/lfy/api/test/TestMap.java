package com.my.lfy.api.test;

import java.util.HashMap;
import java.util.Map;

/**
 * TestMap
 *
 * 参考链接 ：  https://www.toutiao.com/i6934575022568669700/?tt_from=weixin&utm_campaign=client_share&wxshare_count=1&timestamp=1614603238&app=news_article&utm_source=weixin&utm_medium=toutiao_android&use_new_style=1&req_id=2021030120535701013109821255091B91&share_token=cde953db-970d-4bf6-9b37-3756efafcf18&group_id=6934575022568669700
 *
 * @author lfy
 * @date 2021/3/4
 **/
@SuppressWarnings("all")
public class TestMap {

    private static Map<Integer, String> map = new HashMap<>(2);

    public static void main(String[] args) {

        map.put(5, "C");

        new Thread("Thread1") {
            @Override
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();

        new Thread("Thread2") {
            @Override
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();

    }
}
