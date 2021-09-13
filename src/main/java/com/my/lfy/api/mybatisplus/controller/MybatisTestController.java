package com.my.lfy.api.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.my.lfy.api.mybatisplus.entity.MybatisTest;
import com.my.lfy.api.mybatisplus.mapper.MybatisTestMapper;
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
    private MybatisTestMapper testMapper;

    @Autowired
    private IMybatisTestService mybatisTestService;

    @PostMapping("/test")
    public Object test() {
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("getById", mybatisTestService.getById(63));
        resultMap.put("list", mybatisTestService.list());
        resultMap.put("getAll", mybatisTestService.getAll());
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

    @PostMapping("/selectObjects")
    public Object selectObjects() {
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.eq("AGE", 18);
        return testMapper.selectObjs(qw);
    }

    @PostMapping("/selectMaps")
    public Object selectMaps() {
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.eq("AGE", 18);
        return testMapper.selectMaps(qw);
    }

    @PostMapping("/selectCount")
    public Object selectCount() {
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.eq("AGE", 18);
        return testMapper.selectCount(qw);
    }

    @PostMapping("/selectOne")
    public Object selectOne() {
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.eq("AGE", 18);
        return testMapper.selectOne(qw);
    }

    @PostMapping("/selectList")
    public Object selectList() {
        // 需要注意如果你的查询条件查出来的结果不是唯一的值的话会报错。意思就是说期待的值是1或者是空，这是需要控制查询条件
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.ge("AGE", 18);
        return testMapper.selectList(qw);
    }

    @PostMapping("/selectByMap")
    public Object selectByMap() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("AGE", 18);
        return testMapper.selectByMap(map);
    }

    @PostMapping("/selectPage")
    public Object selectPage() {
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.isNotNull("NAME")
                .gt("AGE", 18);
        Page<MybatisTest> page = new Page<>(2, 5);
        return testMapper.selectPage(page, qw);
    }

    @PostMapping("/selectMapsPage")
    public Object selectMapsPage() {
        QueryWrapper<MybatisTest> qw = new QueryWrapper<>();
        qw.isNotNull("NAME")
                .gt("AGE", 18);
        Page page = new Page<>(2, 5);
        return testMapper.selectMapsPage(page, qw);
    }

    @PostMapping("/selectBatchIds")
    public Object selectBatchIds() {
        List<Long> idList = new ArrayList<>();
        idList.add(1L);
        idList.add(2L);
        idList.add(3L);
        return testMapper.selectBatchIds(idList);
    }
}
