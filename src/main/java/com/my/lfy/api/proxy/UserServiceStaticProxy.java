package com.my.lfy.api.proxy;

import java.util.Date;

/**
 * @FileName: UserServiceStaticProxy
 * @Description: 模拟静态代理
 * @Author: Lify
 * @CreateDate: 2022/9/23 16:03
 **/
public class UserServiceStaticProxy implements UserService {

    private final UserService userService;

    public UserServiceStaticProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void sayHello() {
        before();
        // 实际调用
        userService.sayHello();
        after();
    }

    private void before() {
        System.out.printf("log start time [%s] %n", new Date());
    }

    private void after() {
        System.out.printf("log end time [%s] %n", new Date());
    }
}
