package com.my.lfy.api.test.service;

import org.springframework.stereotype.Component;

/**
 * Aservice
 *
 * @author lfy
 * @date 2020/7/13
 **/
@Component
public class Aservice extends AbstractService {
    @Override
    public void print() {
        System.out.println("A--->service");
    }
}
