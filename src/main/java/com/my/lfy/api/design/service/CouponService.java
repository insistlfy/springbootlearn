package com.my.lfy.api.design.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * CouponService
 *
 * @author lfy
 * @date 2021/4/8
 **/
@Slf4j
@Service
public class CouponService {

    /**
     * 添加@EventListener注解，并设置监听的事件为UserRegisterEvent
     *
     * @param event UserRegisterEvent
     */
    @EventListener
    public void addCoupon(UserRegisterEvent event) {
        log.info("给用户【{}】发放优惠券。", event.getUsername());
    }
}
