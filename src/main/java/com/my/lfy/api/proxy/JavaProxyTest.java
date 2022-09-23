package com.my.lfy.api.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @FileName: JavaProxyTest
 * @Description: 动态代理测试
 * @Author: Lify
 * @CreateDate: 2022/9/22 15:25
 **/
public class JavaProxyTest {


    public static void main(String[] args) {
        //看看JDK生成的自动代理类
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
    }

    @Test
    public void proxyTest() {

        UserServiceImpl userService = new UserServiceImpl();
        UserService proxyInstance = (UserService) Proxy.newProxyInstance(JavaProxyTest.class.getClassLoader(),
                new Class[]{UserService.class}, (proxy, method, args) -> {
                    System.out.println("代理前置逻辑");
                    try {
                        return method.invoke(userService, args);
                    } finally {
                        System.out.println("代理后置逻辑");
                    }
                });
        proxyInstance.sayHello();
    }
}
