package com.my.lfy.api.forest.controller;

import com.my.lfy.api.forest.service.ForestService;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ForestController
 *
 * @author lfy
 * @date 2020/7/24
 **/
@Api(tags = "【TEST-FOREST】")
@RestController
@RequestMapping("v1/forest")
public class ForestController {

    @Autowired
    private ForestService forestService;

    @PostMapping()
    @ApiOperation(value = "test-01")
    public JsonResult test01() {

        return forestService.test001();
    }

    @PostMapping("/02")
    @ApiOperation(value = "test-01")
    public JsonResult test02() {

        return forestService.test002();
    }
}
