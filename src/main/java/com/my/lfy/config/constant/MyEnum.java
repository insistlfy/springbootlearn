package com.my.lfy.config.constant;

/**
 * MyEnum
 *
 * @author lfy
 * @date 2021/2/4
 **/
public final class MyEnum {

    private MyEnum() {
    }


    public enum OperType {

        /**
         * 操作日志类型
         */
        OTHER,

        INSERT,

        DELETE,

        UPDATE;
    }
}
