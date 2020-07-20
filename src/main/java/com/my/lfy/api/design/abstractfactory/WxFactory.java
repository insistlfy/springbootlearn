package com.my.lfy.api.design.abstractfactory;

/**
 * WxFactory
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class WxFactory implements Provider {
    @Override
    public ISendMsg create() {
        return new WxSendMsg();
    }
}
