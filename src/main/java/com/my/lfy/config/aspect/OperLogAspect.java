package com.my.lfy.config.aspect;

import com.my.lfy.config.annotation.OperLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * OperLogAspect
 *
 * @author lfy
 * @date 2021/2/4
 **/
@Component
@Aspect
@Slf4j
public class OperLogAspect {

    @Before(value = "@annotation(before)")
    public void before(JoinPoint point, OperLog before) throws Throwable {
        log.info("start exec before...");
    }

    @After(value = "@annotation(after)")
    public void after(JoinPoint point, OperLog after) throws Throwable {
        log.info("start exec after...");
    }

    @Around(value = "@annotation(around)")
    public Object around(ProceedingJoinPoint point, OperLog around) throws Throwable {
        log.info("start exec around...");
        return point.proceed();
    }

    @AfterReturning(value = "@annotation(afterReturning)")
    public void afterReturning(JoinPoint point, OperLog afterReturning) throws Throwable {
        log.info("start exec afterReturning...");
    }

    @AfterThrowing(value = "@annotation(afterThrowing)", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e, OperLog afterThrowing) throws Throwable {
        log.info("start exec afterThrowing...");

        if (null != e) {
            log.error("errorMsg={}......", e.getMessage(), e);
        }
        log.info("end exec afterThrowing...");
    }
}


