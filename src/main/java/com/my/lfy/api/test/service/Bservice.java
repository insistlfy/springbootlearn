package com.my.lfy.api.test.service;

import org.springframework.stereotype.Component;

/**
 * Bservice
 *
 * @author lfy
 * @date 2020/7/13
 **/
@Component("B")
public class Bservice extends AbstractService {
    @Override
    public void print() {
        System.out.println("B--->service");
    }
}
