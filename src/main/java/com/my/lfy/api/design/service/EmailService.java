package com.my.lfy.api.design.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * EmailService
 * 实现ApplicationListener接口，通过泛型设置感兴趣的事件
 *
 * @author lfy
 * @date 2021/4/8
 **/
@Slf4j
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    /**
     * ① ： 实现onApplicationEvent，针对监听的事件UserRegisterEvent事件，进行自定义处理
     * ② ： @Async，声明异步执行
     *
     * @param event UserRegisterEvent
     */
    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent event) {
        log.info("给用户【{}】发送邮件。", event.getUsername());
    }
}
