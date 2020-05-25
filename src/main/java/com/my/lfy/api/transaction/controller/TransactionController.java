package com.my.lfy.api.transaction.controller;

import com.my.lfy.api.transaction.service.TransactionServiceImpl;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TransactionController
 *
 * @author lfy
 * @date 2020/5/25
 **/
@Api(tags = "【TRANSACTION-TEST】")
@RestController
@Validated
@RequestMapping("/v1/transaction/")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping("test001")
    public JsonResult test001(@RequestParam("patId") @NotNull(message = "患者id不能为空") Long patId,
                              @RequestParam("address") @NotBlank(message = "地址不能为空") String address) {
        transactionService.test001(patId, address);
        return new JsonResult<>();
    }

    @PostMapping("test002")
    public JsonResult test002(@RequestParam(value = "name", required = false) @NotBlank(message = "姓名不能为空") String name) {
        return new JsonResult<>(name);
    }
}
