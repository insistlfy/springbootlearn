package com.my.lfy.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @FileName: UserGroup
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/12 15:48
 **/
public class UserGroup implements Serializable {
    private static final long serialVersionUID = 4051729046607844907L;

    private List<User> group = new ArrayList<>();

    public UserGroup(User... users) {
        this.group.addAll(Arrays.asList(users));
    }

    public List<User> getUserList() {
        return group;
    }
}
