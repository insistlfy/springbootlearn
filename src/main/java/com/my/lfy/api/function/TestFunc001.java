package com.my.lfy.api.function;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TestFunc001
 *
 * @author lfy
 * @date 2020/5/21
 **/
public class TestFunc001 {

    public static void main(String[] args) {

        System.out.println(strHandler("  afsdasd", String::trim));
        System.out.println(strHandler("...Learn Java", str -> str.substring(3)));
        System.out.println("========================①===============================");

        Person person = new Person("James", "M");
        consumerMethod(person, (p -> {
            p.setName("Alis");
            p.setSex("F");
        }));
        System.out.println(person);
        System.out.println("========================②===============================");

        People people = new People();
        biConsumerMethod(person, people, BeanUtils::copyProperties);
        System.out.println(people);
        System.out.println("========================③===============================");

        Person person1 = constructor(Person::new);
        person1.setName("Hello");
        People people1 = constructor(People::new);
        people1.setName("World");
        System.out.println(person1);
        System.out.println(people1);
        System.out.println("========================④===============================");


    }


    /**
     * Function 函数是接口,有参数,有返回值
     *
     * @param str      String
     * @param function Function
     * @return String
     */
    public static String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }


    /**
     * Consumer 消费型接口,有参数,无返回值
     *
     * @param person   Person
     * @param consumer Consumer
     */
    public static void consumerMethod(Person person, Consumer<Person> consumer) {
        consumer.accept(person);
    }

    /**
     * Consumer 消费型接口,有参数,无返回值
     *
     * @param person     Person
     * @param people     People
     * @param biConsumer BiConsumer
     */
    public static void biConsumerMethod(Person person, People people, BiConsumer<Person, People> biConsumer) {
        biConsumer.accept(person, people);
    }

    /**
     * 构造器引用
     *
     * @param t   Supplier
     * @param <T> T
     * @return T
     */
    public static <T> T constructor(Supplier<T> t) {
        return t.get();
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
class Person implements Serializable {
    private static final long serialVersionUID = 4747119184867951770L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;
}


@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
class People implements Serializable {
    private static final long serialVersionUID = 4747119184867951770L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;
}