package com.my.lfy.api.test;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试BeanUtils
 *
 * @author lfy
 * @date 2020/6/18
 **/
@Slf4j
public class Test001 {

    public static void main(String[] args) {

        List<A> aList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            A a = A.builder().age(1)
                    .name("a" + i)
                    .sex("F").build();
            aList.add(a);
        }
        log.info("aList=========>{}.", aList);
        System.out.println();

        List<B> bList = new ArrayList<>();
        BeanUtils.copyProperties(aList, bList);
        log.info("bList=========>11{}.", bList);

        aList.forEach(e -> {
            B b = new B();
            BeanUtils.copyProperties(e, b);
            bList.add(b);
        });
        log.info("bList=========>22{}.", bList);
    }
}


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class A {

    private String name;

    private Integer age;

    private String sex;
}

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class B {

    private String name;

    private Integer age;

    private String sex;
}