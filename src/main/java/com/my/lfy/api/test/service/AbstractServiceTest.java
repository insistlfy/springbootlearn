package com.my.lfy.api.test.service;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * AbstractServiceTest
 *
 * @author lfy
 * @date 2020/7/18
 **/
@Slf4j
public abstract class AbstractServiceTest {

    @PostConstruct
    public void init() {
        if (!"lfy".equals(getOperCode())) {
            log.info("init failed...");
            return;
        }
        log.info("init successful...");
    }

    /**
     * test
     *
     * @return String
     */
    public abstract String getOperCode();
}
