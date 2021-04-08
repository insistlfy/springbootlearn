package com.my.lfy.api.design.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * UserService
 * 实现ApplicationEventPublisherAware发布事件
 *
 * @author lfy
 * @date 2021/4/8
 **/
@Slf4j
@Service
public class UserService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username) {

        // 执行注册逻辑
        log.info("{}-register successfully...", username);

        //发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));
    }
}
