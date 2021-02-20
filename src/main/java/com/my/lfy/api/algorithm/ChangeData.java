package com.my.lfy.api.algorithm;

/**
 * Test002 java
 * ① : 实现两个数据交换
 * ② : 与(&) 或(|) 异或(^) 的简单使用
 * 详见: Test002.md
 *
 * @author lfy
 * @date 2021/2/20
 **/
public class ChangeData {

    public static void main(String[] args) {

        lowSwap();

        midSwap();

        highSwap();

        int x = 1 ^ 2;
        int y = 1 & 2;
        int z = 1 | 2;
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
    }

    /**
     * 创建一个临时变量存储两个中某个值
     */
    public static void lowSwap() {
        int a = 100;
        int b = 50;
        System.out.println("第一种方法交换前 : a = " + a);
        System.out.println("第一种方法交换前 : b = " + b);

        int temp = a;
        a = b;
        b = temp;

        System.out.println("第一种方法交换后 : a = " + a);
        System.out.println("第一种方法交换后 : b = " + b);
        System.out.println("===============①=================");
    }

    /**
     * 两数相加保存和值
     */
    public static void midSwap() {
        int a = 100;
        int b = 50;
        System.out.println("第二种方法交换前 : a = " + a);
        System.out.println("第二种方法交换前 : b = " + b);

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("第二种方法交换后 : a = " + a);
        System.out.println("第二种方法交换后 : b = " + b);
        System.out.println("===============②=================");
    }

    /**
     * 两数异或保存两数状态
     */
    public static void highSwap() {
        int a = 100;
        int b = 50;
        System.out.println("第三种方法交换前 : a = " + a);
        System.out.println("第三种方法交换前 : b = " + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("第三种方法交换后 : a = " + a);
        System.out.println("第三种方法交换后 : b = " + b);
        System.out.println("===============③=================");
    }
}
