package com.my.lfy.api.design.easyfactory;

/**
 * Animal
 *
 * @author lfy
 * @date 2020/7/20
 **/
public interface Animal {

    void eat();
}


class Cat implements Animal {

    @Override
    public void eat() {

        System.out.println("Cat eating...");
    }
}


class Dog implements Animal {

    @Override
    public void eat() {

        System.out.println("Dog eating...");
    }
}