package com.my.lfy.api.design.controller;

import com.my.lfy.api.design.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ObserverController
 * 使用观察者模式代替for循环
 *
 * @author lfy
 * @date 2021/4/8
 **/
@Api(tags = "【TEST-OBSERVER】")
@RestController
@RequestMapping("/observer/")
public class ObserverController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public String register(String username) {
        userService.register(username);
        return "success";
    }
}
