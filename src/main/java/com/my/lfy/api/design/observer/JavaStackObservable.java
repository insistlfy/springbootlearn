package com.my.lfy.api.design.observer;

import lombok.Getter;

import java.util.Observable;

/**
 * JavaStackObservable
 * 观察目标类
 *
 * @author lfy
 * @date 2021/4/6
 **/
@Getter
public class JavaStackObservable extends Observable {

    private String article;

    public void publish(String article) {
        // 发表文章
        this.article = article;

        // 改变状态
        this.setChanged();

        //通知所有观察者
        this.notifyObservers();
    }
}
