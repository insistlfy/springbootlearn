package com.my.lfy.api.annotation.controller;

import com.my.lfy.api.annotation.model.BaseInfo;
import com.my.lfy.api.annotation.model.ResultVo;
import com.my.lfy.api.annotation.service.AnnotationService;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AnnotationService annotationService;

    @PostMapping("test01")
    public JsonResult<ResultVo> test(@RequestParam("sid") String sid,
                                     @RequestBody BaseInfo baseInfo) {
        return new JsonResult<>(annotationService.test01(sid, baseInfo));
    }
}
