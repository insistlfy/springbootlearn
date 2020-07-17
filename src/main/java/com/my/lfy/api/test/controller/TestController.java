package com.my.lfy.api.test.controller;

import com.my.lfy.api.test.model.TestModel;
import com.my.lfy.api.test.service.MyFactory;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.utils.JsonResult;
import com.my.lfy.utils.MyEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CommonMapper commonMapper;

    @PostMapping()
    @ApiOperation(value = "test-01")
    public JsonResult test() {
        myFactory.get(MyEnum.TEST.getTest("A")).print();
        return new JsonResult<>();
    }


    @PostMapping("/02")
    @ApiOperation(value = "test-mybatis-list")
    public JsonResult test02() {

        List<String> params = new ArrayList<>();
//        params.add("REG_DIRECT_CHARGE");
        return new JsonResult<>(commonMapper.queryList(params));
    }

    @PostMapping("/03")
    @ApiOperation(value = "test-mybatis-object-list")
    public JsonResult test03() {

        TestModel model = new TestModel();
//        model.setCode(new ArrayList<>());
        return new JsonResult<>(commonMapper.queryListModel(model));
    }
}
