package com.my.lfy.api.timer;

import org.apache.poi.ss.formula.functions.T;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 *
 * @author lfy
 * @date 2020/4/16
 **/
public class TimerTest {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task run =========> " + new Date());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 10, 3000);
    }

}
