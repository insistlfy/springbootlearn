package com.my.lfy.api.design.abstractfactory;

/**
 * SmsSendMsg
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class SmsSendMsg implements IsendMsg {
    @Override
    public void sendMsg() {
        System.out.println("Sms send Msg...");
    }
}
