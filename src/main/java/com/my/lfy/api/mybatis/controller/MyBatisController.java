package com.my.lfy.api.mybatis.controller;

import com.my.lfy.api.mybatis.model.MybatisTest1Vo;
import com.my.lfy.api.mybatis.service.MyBatisService;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MyBatisController
 *
 * @author lfy
 * @date 2021/2/18
 **/
@Slf4j
@RestController
@RequestMapping("v1/mybatis/")
@Api(tags = "【TEST-MYBATIS】")
public class MyBatisController {

    @Autowired
    private MyBatisService myBatisService;

    @PostMapping("test01")
    public JsonResult test01() {
        myBatisService.test01();
        return new JsonResult<>();
    }

    @PostMapping("test02")
    public JsonResult test02() {
        myBatisService.init();
        return new JsonResult<>();
    }

    @PostMapping("test03")
    public JsonResult test03() {
        myBatisService.test03();
        return new JsonResult<>();
    }

    @PostMapping("test04")
    @ApiOperation("测试-SQL92规范中的‘行行比较’")
    public JsonResult test04(@RequestBody List<MybatisTest1Vo> mybatisTest1VoList) {
        return new JsonResult<>(myBatisService.test04(mybatisTest1VoList));
    }

    @PostMapping("test05")
    @ApiOperation("测试@RefreshScope动态刷新")
    public JsonResult<String> test05() {
        return new JsonResult<>(myBatisService.test005());
    }
}
