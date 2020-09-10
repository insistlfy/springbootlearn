package com.my.lfy.api.test.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

/**
 * CardNoTask
 *
 * @author lfy
 * @date 2020/9/10
 **/
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardNoTask extends RecursiveTask<Set<String>> {

    private static final int THRESHOLD = 1000;

    private int size;

    private List<String> dataList;


    @Override
    protected Set<String> compute() {
        List<String> resultList = new ArrayList<>();

        if (size < THRESHOLD) {
            resultList = new ArrayList<>();
        } else {

            int tempSize = size - THRESHOLD;
            CardNoTask leftTask = new CardNoTask(tempSize, dataList.subList(0, THRESHOLD));
            CardNoTask rightTask = new CardNoTask(tempSize, dataList.subList(THRESHOLD, dataList.size()));

            leftTask.fork();
            rightTask.fork();

            Set<String> leftResult = leftTask.join();
            Set<String> rightResult = rightTask.join();

            resultList.addAll(leftResult);
            resultList.addAll(rightResult);

        }
        return new HashSet<>(resultList);
    }

}
