package com.my.lfy.api.algorithm;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * SortAlgorithm
 * 排序算法练习
 * 1、冒泡排序
 * ① 原理：相邻的两个单位,比较存储的数据， 如果第一个单元的数据较大,就将两个相邻单元,交换存储数据
 * ② 过程：从起始单元开始比较,第一次循环,会选择出一个最大值,放在数组所有单元的最后， 之后,每次循环,
 * 都会比较出一个本次循环的最大值,放在当前参与比较单元的最后，之前已经比较选出的单元,不会参与下一次比较
 * <p>
 * 2、选择排序
 * ① 原理：先定义循环的起始位置默认为最小值所在位置
 * ② 过程：从起始位置下一个位置开始,执行循环，如果有位置上的数值,小于,存储索引位置上的数值，就存储这个位置的索引值，循环结束,
 * 比较存储的索引,是否是起始位置索引，如果不是,就交换两个位置上的数值，会将本次循环的最小值,放置在循环的起始位置上，
 * 再执行多次循环,完成排序
 * <p>
 * 3、快速排序：
 * ① 原理：快速排序是基于二分的思想,对冒泡排序的一种改进。主要思想是确立一个基数,将小于基数的数放到基数左边,
 * 大于基数的数字放到基数的右边,然后在对这两部分进一步排序,从而实现对数组的排序
 * <p>
 * 学习连接1：https://blog.csdn.net/LPKJJSHSSB/article/details/105007162
 * 学习连接2：https://www.jb51.net/article/262303.htm
 *
 * @author lfy
 * @CreateDate: 2021/3/1
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
        System.out.println("=================================================");
        System.out.println("新快速排序后：" + JSON.toJSONString(quickSort(arr1, 0, arr.length - 1)));
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
        // 外层循环控制循环的趟数， 最后一个元素不用再循环比较
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环比较相邻的两个数，已经比较过的最大值，无需再参与循环
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
        for (int i = 0; i < arr.length - 1; i++) {

            //初始化一个变量，用来记录最小数字的下标。初始默认假设第一个数字就是最小数字
            int minIndex = i;

            //第二个循环，通过比较获取数组中最小的数字的下标。
            for (int j = i + 1; j < arr.length; j++) {
                //如果找到更小的数字
                if (arr[minIndex] > arr[j]) {
                    //将minIndex变量的值修改为新的最小数字的下标。
                    minIndex = j;
                }
            }
            //将最小的数字替换到第一个位置，将第一个位置的数字放到最小数字原来的位置
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
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

    /**
     * 快速排序 TODO
     *
     * @param sourceArr 待排序数组
     * @param left      int
     * @param right     int
     * @return int[]
     */
    public static int[] quickSort(int[] sourceArr, int left, int right) {
        // 递归的出口必须仔细考虑清楚，否则就会陷入无穷循环从而使栈溢出
        if (left >= right) {
            return sourceArr;
        }
        // 不改变原数组的值
        int[] arr = Arrays.copyOf(sourceArr, sourceArr.length);
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            // 这里如果pivot 选在左侧，就要先从右侧开始遍历，反之则先从左侧开始
            while (arr[j] > pivot && i < j) {
                j--;
            }
            // 找到比基准小的数换到左侧去
            arr[i] = arr[j];
            while (arr[i] < pivot && i < j) {
                i++;
            }
            // 找到比基准大的数换到右侧去
            arr[j] = arr[i];
        }
        // 最后将基准放到中间位置
        arr[i] = pivot;
        //  递归快排左侧数列
        quickSort(arr, left, i - 1);
        // 递归遍历右侧数列
        quickSort(arr, i + 1, right);
        return arr;
    }
}
