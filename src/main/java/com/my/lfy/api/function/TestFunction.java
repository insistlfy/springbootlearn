package com.my.lfy.api.function;

import lombok.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

/**
 * 测试函数式编程
 *
 * @author lfy
 * @date 2020/5/12
 **/
public class TestFunction {
    public static void main(String[] args) {

        IPersion persion = new IPersion() {
            @Override
            public String say(String str) {
                return str;
            }
        };


        IPersion persion1 = str -> str;

//        Function<Integer, User> uf3 = (Integer id) -> new User(id);
//        User apply = uf.apply(61888);

        Function<String, Persion> persionFunction = Persion::new;
        persionFunction.apply("James");
        System.out.println(persionFunction.apply("James"));


        Persion p = Persion.builder().name("Alis").build();
//        Optional.ofNullable(p).flatMap(Persion::getName);
//        String no_name = Optional.ofNullable(p).map(Persion::getName).orElse("no name");

    }
}

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Persion {

    private String name;
}