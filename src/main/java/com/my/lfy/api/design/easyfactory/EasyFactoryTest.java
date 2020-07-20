package com.my.lfy.api.design.easyfactory;

/**
 * EasyFactoryTest
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class EasyFactoryTest {

    public static void main(String[] args) {
        Animal animal = AnimalFactory.getInstance("cat");
        if (null != animal) {
            animal.eat();
        }
    }
}
