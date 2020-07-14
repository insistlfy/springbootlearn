package com.my.lfy.config.aspect;

import com.alibaba.fastjson.JSON;
import com.my.lfy.api.annotation.model.BaseInfo;
import com.my.lfy.config.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * MyAnnotationAspect
 *
 * @author lfy
 * @date 2020/7/14
 **/
@Slf4j
@Aspect
@Component
public class MyAnnotationAspect {

    @Around(value = "@annotation(around)")
    public Object process(ProceedingJoinPoint point, MyAnnotation around) throws Throwable {

        Object[] args = point.getArgs();
        BaseInfo baseInfo = (BaseInfo) args[1];
        baseInfo.setOperCode(around.value());

        log.info("MyAnnotation===> args = [{}].", JSON.toJSONString(args));
        return point.proceed();
    }
}
