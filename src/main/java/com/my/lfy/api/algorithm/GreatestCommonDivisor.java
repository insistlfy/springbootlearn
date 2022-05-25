package com.my.lfy.api.algorithm;

/**
 * describe : 求两个数的最大公约数
 *
 * @author lfy
 * @date 2020/7/24
 **/
public class GreatestCommonDivisor {

    public static void main(String[] args) {

        int a = 12, b = 18;
        System.out.println(a + "和" + b + "最大公约数：" + gcd1(a, b));
        System.out.println(a + "和" + b + "最大公约数：" + gcd2(a, b));
        System.out.println(a + "和" + b + "最小公倍数：" + LeastCommonMultiple.lcm(a, b));
    }

    /**
     * 辗转相除法（欧几里得算法）
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int gcd1(int a, int b) {
        //交换两数
        if (a < b) {
            ChangeData.highSwap(a, b);
        }
        if (a % b == 0) {
            return b;
        }
        return gcd1(b, a % b);
    }

    /**
     * 更相减损法
     *
     * @param a int
     * @param b int
     * @return int
     */
    public static int gcd2(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        if (big == small) {
            return small;
        }
        return gcd2(big - small, small);
    }
}
