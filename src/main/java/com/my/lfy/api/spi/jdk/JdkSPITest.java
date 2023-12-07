package com.my.lfy.api.spi.jdk;

import java.util.ServiceLoader;

/**
 * @FileName: JdkSPITest
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/12/7 16:45
 * @Version: 1.0
 **/
public class JdkSPITest {

    public static void main(String[] args) {
        ServiceLoader<Fruit> fruits = ServiceLoader.load(Fruit.class);
        for (Fruit fruit : fruits) {
            System.out.println(fruit.getName());
        }
    }
}
