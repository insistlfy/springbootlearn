package com.my.lfy.api.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BinaryQuery
 *
 * @author lfy
 * @date 2021/4/14
 **/
public class BinaryQuery {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(10);
        list.add(15);
        list.add(2);
        list.add(2);
        list.add(5);
        list.add(18);

        System.out.println(binaryQuery(list, 18));

    }


    public static int binaryQuery(List<Integer> sortList, int value) {

        sortList = sortList.stream().sorted().collect(Collectors.toList());
        System.out.println(sortList);

        int start = 0;
        int end = sortList.size() - 1;

        while (start <= end) {
            int middle = (start + end) / 2;
            if (value == sortList.get(middle)) {
                return middle;
            }

            if (value < sortList.get(middle)) {
                end = middle - 1;
            }

            if (value > sortList.get(middle)) {
                start = middle + 1;
            }
        }
        return -1;
    }
}
