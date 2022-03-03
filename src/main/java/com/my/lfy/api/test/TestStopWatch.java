package com.my.lfy.api.test;

import org.springframework.util.StopWatch;

/**
 * @FileName: TestStopWatch
 * @Description: 秒表
 *               使用场景：在一个大任务下，可能有多个小的步骤任务，而我们需要知道各个步骤任务的用时情况
 * @Author: Lify
 * @CreateDate: 2022/1/18 18:41
 * @Version: 1.0
 **/
public class TestStopWatch {

    public static void main(String[] args) throws InterruptedException {

        StopWatch stopWatch = new StopWatch("StopWatch");
        for (int i = 0; i < 10; i++) {
            stopWatch.start("Task" + i);
            //Some Real Business Logic
            Thread.sleep(500);
            stopWatch.stop();
        }
        System.out.println(stopWatch.prettyPrint());
    }
}
