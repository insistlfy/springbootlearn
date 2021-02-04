package com.my.lfy.config.annotation;

import com.my.lfy.config.constant.MyEnum;
import io.swagger.annotations.ApiModelProperty;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * OperLog
 *
 * @author lfy
 * @date 2021/2/4
 **/
@Documented
@Target({METHOD})
@Retention(RUNTIME)
public @interface OperLog {

    @ApiModelProperty(value = "操作模块")
    String operModel() default "";

    @ApiModelProperty(value = "操作类型")
    MyEnum.OperType operType() default MyEnum.OperType.OTHER;

    @ApiModelProperty(value = "操作描述")
    String operDesc() default "";

}
