package com.my.lfy.api.test.controller;

import com.my.lfy.api.test.service.MyFactory;
import com.my.lfy.utils.JsonResult;
import com.my.lfy.utils.MyEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author lfy
 * @date 2020/7/13
 **/
@RestController
@RequestMapping("v1/test")
@Api(tags = "【TEST-STRATEGY】")
public class TestController {

    @Autowired
    private MyFactory myFactory;

    @PostMapping()
    @ApiOperation(value = "test-01")
    public JsonResult test() {
        myFactory.get(MyEnum.TEST.getTest("A")).print();
        return new JsonResult<>();
    }
}
