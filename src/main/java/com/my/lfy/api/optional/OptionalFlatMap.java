package com.my.lfy.api.optional;

import com.alibaba.fastjson.JSON;
import com.my.lfy.utils.User;
import com.my.lfy.utils.UserGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @FileName: OptionalFlatMap
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/12 15:46
 **/
public class OptionalFlatMap {

    public static void main(String[] args) {

        //需求：需要将每个UserGroup对象中的那些User类取出来，放到一个ArrayList里面，得到一个List<User>
        List<Stream<User>> userList1 = init().stream()
                .map(e -> e.getUserList().stream())
                .collect(Collectors.toList());

        List<List<User>> userList2 = init().stream()
                .map(UserGroup::getUserList)
                .collect(Collectors.toList());

        List<User> userList3 = init().stream()
                .flatMap(e -> e.getUserList().stream())
                .collect(Collectors.toList());

        System.out.println("userList1: " + JSON.toJSONString(userList1));
        System.out.println("userList2: " + JSON.toJSONString(userList2));
        System.out.println("userList3: " + JSON.toJSONString(userList3));


    }

    /**
     * 初始化测试数据
     *
     * @return List<UserGroup>
     */
    public static List<UserGroup> init() {
        List<UserGroup> userGroupList = new ArrayList<>();
        UserGroup studentGroup = new UserGroup(User.builder()
                .userName("小明")
                .sex("M")
                .type("3")
                .build(), User.builder()
                .userName("小红")
                .sex("F")
                .type("3")
                .build());

        UserGroup farmerGroup = new UserGroup(User.builder()
                .userName("张三")
                .sex("M")
                .type("2")
                .build(), User.builder()
                .userName("李四")
                .sex("F")
                .type("2")
                .build());

        UserGroup workerGroup = new UserGroup(User.builder()
                .userName("王五")
                .sex("M")
                .type("1")
                .build(), User.builder()
                .userName("赵六")
                .sex("F")
                .type("1")
                .build());
        userGroupList.add(studentGroup);
        userGroupList.add(farmerGroup);
        userGroupList.add(workerGroup);
        return userGroupList;
    }
}
