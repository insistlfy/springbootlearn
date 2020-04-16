package com.my.lfy.api.retry.controller;

import com.my.lfy.api.retry.service.RetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * RetryController
 *
 * @author lfy
 * @date 2020/4/14
 **/
@RestController
@Api(tags = "【RETRY-TEST】")
@RequestMapping("/v1/retry/")
@Slf4j
public class RetryController {

    @Autowired
    private RetryService retryService;

    @ApiOperation(value = "test")
    @PostMapping("test")
    public void test(@RequestParam("str") String str) {
        retryService.test(str);
    }
}
