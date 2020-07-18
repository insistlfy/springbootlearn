package com.my.lfy.api.test.service;

import org.springframework.stereotype.Service;

/**
 * AbstractServiceTest01Impl
 *
 * @author lfy
 * @date 2020/7/18
 **/
@Service
public class AbstractServiceTest01Impl extends AbstractServiceTest {


    @Override
    public String getOperCode() {
        return "aaa";
    }
}
