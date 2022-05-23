package com.my.lfy.api.test;

import lombok.Getter;

import java.util.EnumMap;

/**
 * @FileName: TestEnumMap
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/23 11:25
 **/
public class TestEnumMap {

    enum Season {
        SPRING("春天"),
        SUMMER("夏天"),
        FALL("秋天"),
        WINTER("冬天");

        @Getter
        private String name;

        Season(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        EnumMap<Season, Object> enumMap = new EnumMap<>(Season.class);
        enumMap.put(Season.SPRING, "生机盎然的春天");
        enumMap.put(Season.SUMMER, "骄阳如火的夏天");
        enumMap.put(Season.FALL, "硕果累累的秋天");
        enumMap.put(Season.WINTER, "寒风凌冽的冬天");
        enumMap.forEach((k, v) -> System.out.println(k.getName() + ":" + v));
    }
}
