package com.my.lfy.api.test;


import com.alibaba.fastjson.JSON;
import com.my.lfy.api.algorithm.Dichotomy;
import com.my.lfy.api.algorithm.SortAlgorithm;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * @FileName: TestArray
 * @Description: 数组常用操作
 * @Author: Lify
 * @CreateDate: 2022/5/26 9:35
 **/
public class TestArray {

    public static void main(String[] args) {

        int[] sourceArr = {7, 9, 1, 6, 4, 5, 3, 2, 8, 0};
        System.out.println("原数组为：" + JSON.toJSONString(sourceArr));
        // 1. 数组拷贝
        int[] copyArr = Arrays.copyOf(sourceArr, sourceArr.length);
        System.out.println("拷贝后的数组为：" + JSON.toJSONString(copyArr));
        System.out.println("====================================================");

        // 2. 数组拼接
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {4, 5, 6};
        System.out.println("拼接数组arr1为：" + JSON.toJSONString(arr1));
        System.out.println("拼接数组arr2为：" + JSON.toJSONString(arr2));
        int[] splicingArr1 = ArrayUtils.addAll(arr1, arr2);
        System.out.println("第一种-拼接后数组arr为：" + JSON.toJSONString(splicingArr1));

        int[] splicingArr2 = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, splicingArr2, 0, arr1.length);
        System.arraycopy(arr2, 0, splicingArr2, arr1.length, arr2.length);
        System.out.println("第二种-拼接后数组arr为：" + JSON.toJSONString(splicingArr2));
        System.out.println("====================================================");
        // 3. 数组元素冒泡排序/选择排序
        System.out.println("====================================================");
        System.out.println("冒泡排序后:" + JSON.toJSONString(SortAlgorithm.bubbleSort(sourceArr)));

        //4. 数组二分查找元素
        System.out.println("====================================================");
        int[] sortArr = SortAlgorithm.bubbleSort(sourceArr);
        System.out.println("二分查找元素：" + Dichotomy.dichotomy(sortArr, 9));

        //5. 寻找数组中的最大元素和最小元素
        System.out.println("数组中的最大元素为：" + max(sourceArr));
        System.out.println("数组中的最小元素为：" + min(sourceArr));

    }


    /**
     * 获取整型数组中的最大元素
     *
     * @param sourceArr int[]
     * @return int
     */
    public static int max(int[] sourceArr) {
        if (null == sourceArr || sourceArr.length == 0) {
            return Integer.MAX_VALUE;
        }
        int max = sourceArr[0];
        for (int i : sourceArr) {
            if (max <= i) {
                max = i;
            }
        }
        return max;
    }

    /**
     * 获取整型数组中的最小元素
     *
     * @param sourceArr int[]
     * @return int
     */
    public static int min(int[] sourceArr) {
        if (null == sourceArr || sourceArr.length == 0) {
            return Integer.MIN_VALUE;
        }
        int min = sourceArr[0];
        for (int i : sourceArr) {
            if (min >= i) {
                min = i;
            }
        }
        return min;
    }
}
