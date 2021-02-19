package com.my.lfy.api.retrofit.controller;

import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RetrofitController
 *
 * @author lfy
 * @date 2021/2/18
 **/
@Slf4j
@RestController
@RequestMapping("v1/retrofit/")
@Api(tags = "【TEST-RETROFIT】")
public class RetrofitController {

    public JsonResult test01() {
        return new JsonResult<>();
    }
}
