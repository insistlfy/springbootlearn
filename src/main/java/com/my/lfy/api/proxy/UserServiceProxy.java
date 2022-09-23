package com.my.lfy.api.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @FileName: UserServiceProxy
 * @Description: 代理的辅助类
 * @Author: Lify
 * @CreateDate: 2022/9/23 15:44
 **/
public class UserServiceProxy implements InvocationHandler {

    private Object object;

    public UserServiceProxy(Object o) {
        this.object = o;
    }

    /**
     * 实现动态代理，就需要实现InvocationHandler接口中的invoke方法，该方法有三个参数
     *
     * @param proxy：就是动态代理生成的代理对象
     * @param method：就是调用的方法
     * @param args:表示该方法的参数
     * @return Object
     * @throws Throwable e
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理类提供委托类提供的功能，也可以提供自己特有的功能
        System.out.println("donging UserServiceProxy.invoke");
        //调用委托类提供的方法
        method.invoke(object, args);
        System.out.println("donging UserServiceProxy.invoke end");
        return null;
    }
}
