package com.my.lfy.api.thread.controller;

import com.my.lfy.api.thread.service.ThreadPoolServiceImpl;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ThreadPoolController
 *
 * @author lfy
 * @date 2020/5/23
 **/
@RestController
@Api(tags = "【THREAD-POOL-TEST】")
@RequestMapping("/v1/thread/pool")
@Slf4j
public class ThreadPoolController {

    @Autowired
    private ThreadPoolServiceImpl threadPoolService;

    @PostMapping("/test01")
    @ApiOperation(value = "线程池测试一")
    public String test01(@RequestParam("str") String string) {
        return threadPoolService.test01(string);
    }

    @PostMapping("/test02")
    @ApiOperation(value = "线程池测试二")
    public JsonResult test02() {
        return new JsonResult<>(threadPoolService.test02());
    }
}
