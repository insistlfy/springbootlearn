package com.my.lfy.utils;

import com.my.lfy.exception.ServiceException;
import lombok.Getter;

/**
 * MyEnum
 *
 * @author lfy
 * @date 2020/7/16
 **/
public final class MyEnum {

    /**
     * TEST
     */
    public enum TEST {

        /**
         *
         */
        A("A", "A<-->Service"),
        B("B", "A<-->Service");

        @Getter
        String id;

        @Getter
        String name;

        TEST(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public static TEST getTest(String id) {
            for (TEST t : TEST.values()) {
                if (t.getId().equals(id)) {
                    return t;
                }
            }
            throw new ServiceException("不支持的类型");
        }
    }
}
