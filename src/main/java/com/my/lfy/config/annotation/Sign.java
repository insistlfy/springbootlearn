package com.my.lfy.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Sign
 *
 * @author lfy
 * @date 2021/2/1
 **/
@Documented
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Sign {
}
