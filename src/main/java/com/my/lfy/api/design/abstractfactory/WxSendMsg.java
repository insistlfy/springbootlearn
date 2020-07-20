package com.my.lfy.api.design.abstractfactory;

/**
 * WxSendMsg
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class WxSendMsg implements ISendMsg {
    @Override
    public void sendMsg() {
        System.out.println("WX send msg...");
    }
}
