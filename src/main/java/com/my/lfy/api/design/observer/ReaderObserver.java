package com.my.lfy.api.design.observer;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Observable;
import java.util.Observer;

/**
 * ReaderObserver
 * 观察者
 *
 * @author lfy
 * @date 2021/4/6
 **/
@Slf4j
@RequiredArgsConstructor
public class ReaderObserver implements Observer {

    @NonNull
    private String name;

    private String article;

    @Override
    public void update(Observable o, Object arg) {

        // 更新文章
        updateArticle(o);
    }

    private void updateArticle(Observable o) {
        JavaStackObservable javaStackObservable = (JavaStackObservable) o;
        this.article = javaStackObservable.getArticle();
        log.info("I am Reader : 【{}】,Article is updated，【{}】", this.name, this.article);
    }
}
