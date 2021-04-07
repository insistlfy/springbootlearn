package com.my.lfy.api.thread;

/**
 * TestBit
 *
 * @author lfy
 * @date 2021/4/7
 **/
public class TestBit {

    public static void main(String[] args) {

        int i = 2 << 3;
        int j = 8 >> 2;
        System.out.println(i);
        System.out.println(j);
        System.out.println("===============================");

        int x = 8 & 0x07;
        System.out.println(x);
        System.out.println("===============================");

        int y = x | j;
        System.out.println(y);
        y |= j;
        System.out.println(y);
        System.out.println("===============================");

        int z = i ^ j;
        i ^= j;
        System.out.println(z);
        System.out.println(i);
    }
}
