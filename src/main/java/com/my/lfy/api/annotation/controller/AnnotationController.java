package com.my.lfy.api.annotation.controller;

import com.my.lfy.api.annotation.model.BaseInfo;
import com.my.lfy.config.annotation.MyAnnotation;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * AnnotationController
 *
 * @author lfy
 * @date 2020/7/14
 **/
@Api(tags = "【ANNOTATION-TEST】")
@RestController
@RequestMapping("v1/annotation/")
public class AnnotationController {

    @MyAnnotation
    @PostMapping("test01")
    public JsonResult test(@RequestParam("sid") String sid,
                           @RequestBody BaseInfo baseInfo) {
        return new JsonResult<>(baseInfo.getOperCode());
    }
}
