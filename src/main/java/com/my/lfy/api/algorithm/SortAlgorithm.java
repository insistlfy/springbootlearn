package com.my.lfy.api.algorithm;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SortAlgorithm
 * 排序算法练习
 *
 * @author lfy
 * @date 2021/3/1
 **/
public class SortAlgorithm {

    public static void main(String[] args) {

        int[] sourceArr = {6, 7, 1, 4, 9, 5};
        int[] sourceArr1 = {8, 7, 1, 4, 3, 5};
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        int[] arr1 = Arrays.copyOf(sourceArr1, sourceArr1.length);
        System.out.println("排序前：" + JSON.toJSONString(arr));
        System.out.println("冒泡排序后：" + JSON.toJSONString(bubbleSort(arr)));
        System.out.println("选择排序后：" + JSON.toJSONString(selectionSort(arr)));
        System.out.println("=================================================");
        System.out.println("排序前：" + JSON.toJSONString(arr1));
        System.out.println("选择排序后：" + JSON.toJSONString(selectionSort(arr1)));
        System.out.println("冒泡排序后：" + JSON.toJSONString(bubbleSort(arr1)));
        System.out.println("=================================================");
        System.out.println("排序前：" + JSON.toJSONString(arr1));
        System.out.println("快速排序后：" + JSON.toJSONString(quickSort(arr, 5)));
        System.out.println("快速排序后：" + JSON.toJSONString(quickSort(arr1, 4)));
    }

    /**
     * 冒泡排序
     *
     * @param sourceArr int[]
     * @return int[]
     */
    public static int[] bubbleSort(int[] sourceArr) {
        if (null == sourceArr || sourceArr.length == 0) {
            return sourceArr;
        }
        // 不改变原数组的值
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        // 外层循环控制循环的趟数
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环比较相邻的两个数
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     *
     * @param sourceArr int[]
     * @return int[]
     */
    public static int[] selectionSort(int[] sourceArr) {
        if (null == sourceArr || sourceArr.length == 0) {
            return sourceArr;
        }
        // 不改变原数组的值
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        // 外层循环的每一个元素和内循环的数进行比较
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
        return arr;
    }

    /**
     * 快速排序
     *
     * @param sourceArr 待排序数组
     * @param pivot     基准
     * @return 排序后的数组
     */
    public static int[] quickSort(int[] sourceArr, int pivot) {
        if (null == sourceArr || sourceArr.length == 0) {
            return sourceArr;
        }
        // 不改变原数组的值
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (int i : arr) {
            if (i < pivot) {
                leftList.add(i);
            } else {
                rightList.add(i);
            }
        }
        Integer[] tempLeftArr = leftList.toArray(new Integer[0]);
        int[] leftArr = Arrays.stream(tempLeftArr).mapToInt(Integer::intValue).toArray();
        Integer[] tempRightArr = rightList.toArray(new Integer[0]);
        int[] rightArr = Arrays.stream(tempRightArr).mapToInt(Integer::intValue).toArray();
        int[] bubbleLeftSort = bubbleSort(leftArr);
        int[] bubbleRightSort = bubbleSort(rightArr);

        return ArrayUtils.addAll(bubbleLeftSort, bubbleRightSort);
    }
}
