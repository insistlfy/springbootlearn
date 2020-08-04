package com.my.lfy.api.algorithm;

/**
 * java <---> 实现斐波那契数列
 * ==================> A[0] = 0; A[1] = 1; A[2] = 2;
 * ==================> A[n] = A[n-1] + A[n-2] (n>=3)
 *
 * @author lfy
 * @date 2020/6/22
 **/
public class FibonacciSeries {

    public static void main(String[] args) {

        //first
        for (int i = 0; i < 39; i++) {
            System.out.print(fibonacciSeries(i) + "\t");
        }
        System.out.println();

        //second
        fibonacciSeriesSecond(39);

        System.out.println();

        //third
        fibonacciSeriesThird(39);

    }

    public static int fibonacciSeries(int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return fibonacciSeries(n - 1) + fibonacciSeries(n - 2);
    }

    public static void fibonacciSeriesSecond(int n) {
        int[] arr = new int[n];

        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;

        for (int i = 0; i < n; i++) {
            if (i > 1) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            System.out.print(arr[i] + "\t");
        }
    }

    public static void fibonacciSeriesThird(int n) {

        int first = 1;
        int second = 1;
        int reslut;

        System.out.print(0 + "\t" + 1 + "\t" + 1 + "\t");

        for (int i = 3; i < n; i++) {

            reslut = first + second;

            first = second;

            second = reslut;
            System.out.print(reslut + "\t");

        }
    }

}
