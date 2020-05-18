package com.my.lfy.api.function;

/**
 * IPersion
 *
 * @author lfy
 * @date 2020/5/12
 **/
@FunctionalInterface
public interface IPersion {

    String say(String str);

    static void run(String str) {
        System.out.println("run...................");
    }

    static void walk(String str) {
        System.out.println("walk...................");
    }

    default void eat(String str) {
        System.out.println("eat...................");
    }
}
