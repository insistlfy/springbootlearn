package com.my.lfy.api.test.service;

import com.my.lfy.utils.MyEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MyFactory
 *
 * @author lfy
 * @date 2020/7/16
 **/
@Component
public class MyFactory {

    @Autowired
    private final Map<String, AbstractService> context = new ConcurrentHashMap<>();

    public AbstractService get(MyEnum.TEST test) {
        return context.get(test.getId());
    }
}
