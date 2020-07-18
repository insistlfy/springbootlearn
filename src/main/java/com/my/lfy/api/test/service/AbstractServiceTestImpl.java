package com.my.lfy.api.test.service;

import org.springframework.stereotype.Service;

/**
 * AbstractServiceTestImpl
 *
 * @author lfy
 * @date 2020/7/18
 **/
@Service
public class AbstractServiceTestImpl extends AbstractServiceTest {


    @Override
    public String getOperCode() {

        return "lfy";
    }
}
