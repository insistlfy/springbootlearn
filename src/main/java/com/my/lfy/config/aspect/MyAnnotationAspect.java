package com.my.lfy.config.aspect;

import com.alibaba.fastjson.JSON;
import com.my.lfy.api.annotation.model.BaseInfo;
import com.my.lfy.api.annotation.model.ResultVo;
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

        //入参增强
        for (Object arg : args) {
            if (arg instanceof BaseInfo) {
                BaseInfo baseInfo = (BaseInfo) arg;
                baseInfo.setOperCode(around.value());
            }
        }

        log.info("MyAnnotation===> args = [{}].", JSON.toJSONString(args));
        Object proceed = point.proceed();

        //返回值增强
        if (proceed instanceof ResultVo) {
            ResultVo resultVo = (ResultVo) proceed;
            resultVo.setName("姓名 : " + resultVo.getName());
            resultVo.setSex("性别 : " + resultVo.getSex());
            resultVo.setWeight("体重 : " + resultVo.getWeight());
        }
        return proceed;
    }
}
