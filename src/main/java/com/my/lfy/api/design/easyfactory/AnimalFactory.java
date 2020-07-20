package com.my.lfy.api.design.easyfactory;

/**
 * AnimalFactory
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class AnimalFactory {

    public static Animal getInstance(String type) {
        Animal animal = null;
        if ("cat".equals(type)) {
            animal = new Cat();
        }
        if ("dog".equals(type)) {
            animal = new Dog();
        }
        return animal;
    }
}
