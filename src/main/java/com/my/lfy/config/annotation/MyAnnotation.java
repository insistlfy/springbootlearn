package com.my.lfy.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * MyAnnotation
 *
 * @author lfy
 * @date 2020/7/14
 **/
@Documented
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface MyAnnotation {

    String value() default "lfy";
}
