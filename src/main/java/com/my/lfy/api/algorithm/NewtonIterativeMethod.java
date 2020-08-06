package com.my.lfy.api.algorithm;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * NewtonIterativeMethod
 * 使用牛顿迭代法求一个的立方根
 *
 * @author lfy
 * @date 2020/8/6
 **/
public class NewtonIterativeMethod {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        double input = scan.nextDouble();
        DecimalFormat df = new DecimalFormat("0.0");
        System.out.println(df.format(Math.pow(input, 1.0 / 3.0)));
        System.out.println("==================================================");

        System.out.println(getCubeRoot(input));
    }

    public static double getCubeRoot(double source) {

        if (source == 0) {
            return 0;
        }

        double num1, num2;
        num1 = source;
        num2 = ((2 * num1 / 3) + (source / (num1 * num1 * 3)));
        while (Math.abs(num2 - num1) > 0.000001) {
            num1 = num2;
            num2 = (2 * num1 / 3) + (source / (num1 * num1 * 3));
        }

        DecimalFormat df = new DecimalFormat("#.0");
        return Double.parseDouble(df.format(num2));
    }
}
