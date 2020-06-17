package com.my.lfy.api.transaction.controller;

import com.my.lfy.api.transaction.service.ServiceA;
import com.my.lfy.api.transaction.service.TransactionInterfaceService;
import com.my.lfy.utils.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

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
    private ServiceA transactionService;

    @Autowired
    private TransactionInterfaceService transactionInterfaceService;

    @PostMapping("testReadonly")
    public JsonResult testReadonly() {
        transactionService.testReadonly();
        return new JsonResult<>();
    }

    @PostMapping("test002")
    public JsonResult test002(@RequestParam(value = "name", required = false) @NotBlank(message = "姓名不能为空") String name) {
        transactionService.test002();
        return new JsonResult<>();
    }

    @PostMapping("test003")
    public JsonResult test003() {
        transactionService.test003();
        return new JsonResult<>();
    }

    @PostMapping("test004--模拟线上案例")
    public JsonResult checkUnfreezeCharge() {
        transactionService.checkUnfreezeCharge();
        return new JsonResult<>();
    }

    @PostMapping("test005--事务传播")
    public JsonResult testPropagation() {
        transactionService.testPropagation();
        return new JsonResult<>();
    }

    @PostMapping("test006")
    public JsonResult testInterface() {
        transactionInterfaceService.testInterface();
        return new JsonResult<>();
    }
}
