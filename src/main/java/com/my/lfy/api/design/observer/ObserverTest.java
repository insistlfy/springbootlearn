package com.my.lfy.api.design.observer;

/**
 * ObserverTest
 *
 * @author lfy
 * @date 2021/4/6
 **/
public class ObserverTest {

    public static void main(String[] args) {

        // 创建一个观察目标
        JavaStackObservable javaStackObservable = new JavaStackObservable();

        //添加观察者
        javaStackObservable.addObserver(new ReaderObserver("James"));
        javaStackObservable.addObserver(new ReaderObserver("Rose"));
        javaStackObservable.addObserver(new ReaderObserver("Curry"));

        //发布文章
        javaStackObservable.publish("What Observer Model?");
    }
}
