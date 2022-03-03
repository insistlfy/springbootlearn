package com.my.lfy.api.test;

import org.springframework.util.StopWatch;

/**
 * @FileName: TestStopWatch
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/1/18 18:41
 * @Version: 1.0
 **/
public class TestStopWatch {

    public static void main(String[] args) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        for (int i = 0; i < 10; i++) {
            stopWatch.start("Task" + i);
            //Some Real Business Logic
            Thread.sleep(1000);
            stopWatch.stop();
        }
        System.out.println(stopWatch.prettyPrint());;
    }
}
