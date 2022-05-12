package com.my.lfy.api.optional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.lfy.utils.Student;
import com.my.lfy.utils.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @FileName: OptionalLearn
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/11 11:16
 **/
@Slf4j
public class OptionalLearn {

    public static void main(String[] args) {
        // 获取初始化测试数据
        List<User> list = getUserList();
        // 注意匹配不到返回空集合，非null
        List<User> userList = list.stream()
                .filter(e -> "abc".equals(e.getUserName()))
                .collect(Collectors.toList());
        log.info("collect:{},size:{}.", JSON.toJSONString(userList, SerializerFeature.WriteMapNullValue), userList.size());
        Optional.of(userList).ifPresent(e -> e.forEach(s -> log.info("userList:{}。", JSON.toJSONString(s))));
        log.info("======================================================");

        List<String> userNameList = list.stream()
                .map(User::getUserName)
                .map(String::toUpperCase)
                .map(e -> "--".concat(e).concat("--"))
                .collect(Collectors.toList());
        log.info("userNameList:{}。", JSON.toJSONString(userNameList, SerializerFeature.WriteMapNullValue));
        List<String[]> collect1 = userNameList.stream()
                .map(e -> e.split(""))
                .collect(Collectors.toList());
        List<Stream<String>> collect2 = userNameList.stream()
                .map(e -> e.split(""))
                .map(Arrays::stream)
                .collect(Collectors.toList());
        List<String> collect3 = userNameList.stream()
                .map(e -> e.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        log.info("collect1:{}.", JSON.toJSONString(collect1));
        log.info("collect2:{}.", JSON.toJSONString(collect2));
        log.info("collect3:{}.", JSON.toJSONString(collect3));
        log.info("======================================================");

        List<Student> studentList = list.stream().filter(e -> "3".equals(e.getType())).map(s -> {
            Student student = Student.builder()
                    .name(s.getUserName())
                    .sex(s.getSex())
                    .build();
            return student;
        }).collect(Collectors.toList());
//        studentList.stream().flatMap().
        log.info("studentList:{}.", JSON.toJSONString(studentList));

    }

    /**
     * 初始化测试数据
     *
     * @return List
     */
    private static List<User> getUserList() {
        ArrayList<User> list = new ArrayList<>();
        User user1 = User.builder()
                .age(9)
                .userName("小明")
                .sex("F")
                .type("3")
                .build();
        User user2 = User.builder()
                .age(8)
                .userName("小红")
                .sex("F")
                .type("3")
                .build();
        User user3 = User.builder()
                .age(40)
                .userName("张三")
                .sex("F")
                .type("1")
                .build();
        User user4 = User.builder()
                .age(28)
                .userName("李四")
                .sex("M")
                .type("1")
                .build();
        User user5 = User.builder()
                .age(32)
                .userName("王五")
                .sex("M")
                .type("2")
                .build();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        list.add(user5);
        return list;
    }
}
