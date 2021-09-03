package com.my.lfy.api.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.my.lfy.api.mybatisplus.entity.MybatisTest;
import com.my.lfy.api.mybatisplus.service.IMybatisTestService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * test 前端控制器
 * </p>
 *
 * @author Lify
 * @since 2021-09-02
 */
@RestController
@RequestMapping("/mybatisplus/mybatis-test")
@Api(tags = "【MYBATIS-PLUS】")
public class MybatisTestController {

    @Autowired
    private IMybatisTestService mybatisTestService;

    @PostMapping("/test")
    public Object test() {
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("getById", mybatisTestService.getById(63));
        resultMap.put("list", mybatisTestService.list());
        resultMap.put("getAll", mybatisTestService.getAll());
//        resultMap.put("query", mybatisTestService.query().eq("NAME", "James"));
        return resultMap;
    }

    @PostMapping("/save")
    public Object save() {
        MybatisTest test = MybatisTest.builder()
                .name("test")
                .age(25)
                .build();
        mybatisTestService.save(test);
        return "success";
    }

    @PostMapping("/saveBatch")
    public Object saveBatch() {
        List<MybatisTest> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MybatisTest mybatisTest = MybatisTest.builder()
                    .name("test" + i)
                    .age(25 + i)
                    .build();
            list.add(mybatisTest);
        }
        mybatisTestService.saveBatch(list);
        return "success";
    }

    @PostMapping("/delete")
    public String delete() {
        mybatisTestService.deleteById(7);
        return "success";
    }

    @PostMapping("/page")
    public Object getByPage() {
        return PageHelper.startPage(1, 10)
                .doSelectPageInfo(() -> mybatisTestService.list());
    }

    @PostMapping("/queryWrapper")
    public Object queryWrapper() {

        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.select("AGE,NAME").eq("AGE", 18);
        return mybatisTestService.getMap(qw);
    }
}
