package com.my.lfy.api.design.service;

import org.springframework.context.ApplicationEvent;

/**
 * UserRegisterEvent
 * 继承ApplicationEvent ，定义自定义事件，
 *
 * @author lfy
 * @date 2021/4/8
 **/
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 用户名
     */
    private String username;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
