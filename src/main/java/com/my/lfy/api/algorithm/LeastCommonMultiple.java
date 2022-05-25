package com.my.lfy.api.algorithm;

import java.util.Scanner;

/**
 * describe : 求两个数的最小公倍数
 * [a,b] * (a,b) = a * b
 *
 * @author lfy
 * @date 2020/7/24
 **/
public class LeastCommonMultiple {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println("a : " + a + ", b : " + b + "最小公倍数是 : " + min(a, b));
    }

    public static int min(int a, int b) {
        int max = GreatestCommonDivisor.getValue2(a, b);
        return a * b / max;
    }
}
