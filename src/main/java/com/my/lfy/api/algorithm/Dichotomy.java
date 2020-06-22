package com.my.lfy.api.algorithm;

/**
 * java <---> 二分法实现
 * 优点 :
 * 缺点 :
 *
 * @author lfy
 * @date 2020/6/22
 **/
public class Dichotomy {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(dichotomy(arr, 4));
    }

    public static int dichotomy(int[] array, int value) {

        //定义最大/最小索引
        int max = array.length - 1;
        int min = 0;

        //计算中间索引
        int mid = (max + min) / 2;

        while (array[mid] != value) {
            if (array[mid] > value) {
                max = mid - 1;
            } else if (array[mid] < value) {
                mid = mid + 1;
            } else {
                //如果数据中没有目标元素 ,则返回-1
                if (min > max) {
                    return -1;
                }
                //更改mid值
                mid = (max + min) / 2;
            }
        }
        return mid;
    }
}
