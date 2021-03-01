package com.my.lfy.api.test;

import lombok.extern.slf4j.Slf4j;

/**
 * TestFor
 * for 循环练习
 * <p>
 * 问题 ：
 * 当有多个for循环的时候，如何跳出最外层循环？
 *
 * @author lfy
 * @date 2021/3/1
 **/
@Slf4j
public class TestFor {

    private static final int MAX_COUNT = 6;

    public static void main(String[] args) {

        for1();
        log.info("===============================================");
        for2();
        log.info("===============================================");
        for3();
        log.info("===============================================");
        for4();
        log.info("===============================================");
        for5();
        log.info("===============================================");
        for6();
        log.info("===============================================");

    }

    public static void for1() {
        for (int i = 0; i < MAX_COUNT; i++) {
            log.info("for1----i={}.", i);
        }
    }

    public static void for2() {
        for (int i = 0; i < MAX_COUNT; i++) {
            if (i == 4) {
                //跳出本层循环
                break;
            }
            log.info("for2----i={}.", i);
        }
    }

    public static void for3() {
        for (int i = 0; i < MAX_COUNT; i++) {
            if (i == 4) {
                //跳过本次循环
                continue;
            }
            log.info("for3----i={}.", i);
        }
    }

    public static void for4() {

        for (int i = 0; i < MAX_COUNT; i++) {
            log.info("for4--outer----i={}.", i);
            for (int j = 0; j < MAX_COUNT; j++) {

                if (j == 4) {
                    //注意，此处只跳出内循环
                    break;
                }
                log.info("for4--inner----j={}.", j);
            }
        }
    }

    public static void for5() {

        for (int i = 0; i < MAX_COUNT; i++) {
            log.info("for5--outer----i={}.", i);
            for (int j = 0; j < MAX_COUNT; j++) {
                if (j == 4) {
                    //此处只跳过本次内循环
                    continue;
                }
                log.info("for5--inner----j={}.", j);
            }
        }
    }

    /**
     * 跳出指定层循环
     */
    public static void for6() {

        outer:
        for (int i = 0; i < MAX_COUNT; i++) {
            log.info("for6--outer----i={}.", i);

            inner:
            for (int j = 0; j < MAX_COUNT; j++) {

                if (j == 4) {
                    break inner;
                }

                if (i == 3) {
                    break outer;
                }
                log.info("for6--inner----j={}.", j);
            }
        }
    }
}
