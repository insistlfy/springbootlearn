package com.my.lfy.api.annotation.service;

import com.my.lfy.api.annotation.model.BaseInfo;
import com.my.lfy.config.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * AnnotationService
 *
 * @author lfy
 * @date 2020/7/14
 **/
@Slf4j
@Service
public class AnnotationService {

    @MyAnnotation
    public String test01(String sid, BaseInfo baseInfo) {
        String operCode = baseInfo.getOperCode();
        log.info("operCode====>{}.", operCode);
        return operCode;
    }
}
