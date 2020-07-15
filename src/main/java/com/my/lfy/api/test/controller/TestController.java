package com.my.lfy.api.test.controller;

import com.my.lfy.api.test.service.AbstractService;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TestController
 *
 * @author lfy
 * @date 2020/7/13
 **/
@RestController
@RequestMapping("v1/test")
@Api(tags = "【TEST】")
public class TestController {

    @Autowired
    private final Map<String, AbstractService> context = new ConcurrentHashMap<>();

    @PostMapping()
    @ApiOperation(value = "test-01")
    public JsonResult test() {
        context.get("A").print();
        return new JsonResult<>();
    }
}
