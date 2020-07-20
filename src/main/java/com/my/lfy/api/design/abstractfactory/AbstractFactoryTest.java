package com.my.lfy.api.design.abstractfactory;

/**
 * AbstractFactoryTest
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class AbstractFactoryTest {

    public static void main(String[] args) {

        Provider provider = new SmsFactory();
        provider.create().sendMsg();
    }
}
