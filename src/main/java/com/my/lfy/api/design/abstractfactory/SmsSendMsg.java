package com.my.lfy.api.design.abstractfactory;

/**
 * SmsSendMsg
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class SmsSendMsg implements ISendMsg {
    @Override
    public void sendMsg() {
        System.out.println("Sms send Msg...");
    }
}
