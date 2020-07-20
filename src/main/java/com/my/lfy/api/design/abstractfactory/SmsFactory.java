package com.my.lfy.api.design.abstractfactory;

/**
 * SmsFactory
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class SmsFactory implements Provider {
    @Override
    public IsendMsg create() {
        return new SmsSendMsg();
    }
}
