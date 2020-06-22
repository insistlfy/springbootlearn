package com.my.lfy.api.algorithm;

/**
 * java <---> 实现杨辉三角
 *
 * @author lfy
 * @date 2020/6/22
 **/
public class PascalTriangle {

    public static void main(String[] args) {

        pascalTriangle();
    }

    public static void pascalTriangle() {


        int rows = 10;
        for (int i = 0; i < rows; i++) {

            int number = 1;
            System.out.format("%" + (rows - i) * 2 + "s", "");

            for (int j = 0; j <= i; j++) {

                System.out.format("%4d", number);
                number = number * (i - j) / (j + 1);
            }

            System.out.println();
        }
    }
}
