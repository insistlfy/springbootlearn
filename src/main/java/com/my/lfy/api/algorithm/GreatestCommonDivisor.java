package com.my.lfy.api.algorithm;

/**
 * describe : 求两个数的最大公约数
 *
 * @author lfy
 * @date 2020/7/24
 **/
public class GreatestCommonDivisor {

    public static void main(String[] args) {

        System.out.println(getValue1(1, 2));
        System.out.println(getValue2(1, 2));
    }

    /**
     * 辗转相除法（欧几里得算法）
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int getValue1(int a, int b) {

        //交换两数
        if (a < b) {

            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }

        if (a % b == 0) {
            return b;
        }

        return getValue1(b, a % b);
    }

    /**
     * 更相减损法
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int getValue2(int a, int b) {

        int big = Math.max(a, b);
        int small = Math.min(a, b);

        if (big == small) {
            return small;
        }

        return getValue2(big - small, small);
    }
}
