package com.my.lfy.config.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * ActionAspect
 *
 * @author Lifuyuan
 * @date 19-3-21
 **/
@Component
@Aspect
@Slf4j
public class ActionAspect {

    private static final String OPTIONS = "options";

    private static final Integer MAX_LENGTH = 1024;

    /**
     * pointcut  all of the action all methods. 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
     */
    @Pointcut(value = "execution(* com.my.lfy.api..*.*Action..*(..)) || execution(* com.my.lfy.api"
            + "." + ".*.*Controller..*(..))")
    public void aspect() {
    }

    /**
     * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
     */
    @Before("aspect()")
    public void before(JoinPoint joinPoint) {
        log.info("the  request details:");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //log 请求内容
        log.info("请求url:  {}", request.getRequestURL().toString());
        log.info("请求方法:  {}", request.getMethod());
        String className = joinPoint.getSignature().getDeclaringTypeName();

        log.info("执行的类名: {}", "(" + className.substring(className.lastIndexOf(".") + 1) + ".java:1)");
        log.info("执行的方法名: {}", joinPoint.getSignature().getName());
        log.info("参数: {}", joinPoint.getArgs());
        //log 方法参数
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            log.info(paraName + ":    {}", request.getParameter(paraName));
        }
    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();
        String method = request.getMethod().toLowerCase();

        try {
            //对于业务抛出异常的记录就处理
            long start = System.currentTimeMillis();
            Object rvt = jp.proceed(args);
            long end = System.currentTimeMillis();
            log.info("-----------------------------------");
            log.info("执行时间为[{}]ms", (end - start));
            log.info("-----------------------------------");

            if (OPTIONS.equals(method)) {
                return rvt;
            }
            //设置返回参数
            if (rvt != null) {
                log.info(JSON.toJSONString(rvt));
            }
            return rvt;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * 配置后置通知,使用在方法aspect()上注册的切入点
     */
    @After(value = "aspect()")
    public void after() {
    }
}
