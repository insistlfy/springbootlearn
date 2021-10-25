package com.my.lfy.api.thread;

/**
 * @FileName: Test005
 * @Description：TODO
 * @Author: Lify
 * @Created: 2021/10/13 18:31
 * @Versions: V3.0
 * @Company: ©2021东方微银科技（西安）有限公司
 */
public class Test005 {

    public static void main(String[] args) {
        testThread();
        System.out.println("test...");
    }

    private static void testThread() {

        try {
            new Thread(() -> {
                int i = 1 / 0;
                System.out.println("test thread...");
            }).start();
        } catch (Exception e) {
            System.out.println(e);
        }
        String str = "Hello World";
        System.out.println(str);
    }
}
