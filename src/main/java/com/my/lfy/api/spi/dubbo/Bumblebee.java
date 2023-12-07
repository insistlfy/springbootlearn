package com.my.lfy.api.spi.dubbo;

/**
 * @FileName: Bumblebee
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/12/7 17:06
 * @Version: 1.0
 **/
public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
