package com.my.lfy.api.function;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.function.*;

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
        biConsumerMethod(person, people, (person12, people12) -> {
            BeanUtils.copyProperties(person12, people12);
            people12.setWeight("100");
        });
        System.out.println(people);
        System.out.println("========================③===============================");

        Person person1 = constructor(Person::new);
        person1.setName("Hello");
        People people1 = constructor(People::new);
        people1.setName("World");
        System.out.println(person1);
        System.out.println(people1);
        System.out.println("========================④===============================");

        Animal animal1 = biFunctionMethod(person, people, (p1, p2) -> {
            Animal animal = constructor(Animal::new);
            animal.setName(p1.getName());
            animal.setSex(p1.getSex());
            animal.setWeight(p2.getWeight());
            return animal;
        });
        System.out.println(animal1);
        System.out.println("========================⑤===============================");

        People people3 = functionMethod(person, p -> {
            People people2 = constructor(People::new);
            people2.setName(p.getName());
            people2.setSex(p.getSex());
            return people2;
        });
        System.out.println(people3);
        System.out.println("========================⑥===============================");
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
     * Function 函数是接口,有参数,有返回值
     *
     * @param t        T
     * @param function Function
     * @return R
     */
    public static <T, R> R functionMethod(T t, Function<T, R> function) {
        return function.apply(t);
    }

    /**
     * BiFunction 函数是接口,有参数,有返回值
     *
     * @param t          T
     * @param u          U
     * @param biFunction BiFunction
     * @param <T>        T
     * @param <U>        U
     * @param <R>        R
     * @return R
     */
    public static <T, U, R> R biFunctionMethod(T t, U u, BiFunction<T, U, R> biFunction) {
        return biFunction.apply(t, u);
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

    @ApiModelProperty(value = "体重")
    private String weight;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
class Animal implements Serializable {
    private static final long serialVersionUID = 4747119184867951770L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "体重")
    private String weight;
}