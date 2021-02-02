package com.my.lfy.api.test.controller;

import com.my.lfy.api.test.model.TestModel;
import com.my.lfy.api.test.service.MyFactory;
import com.my.lfy.api.test.service.TestService;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.config.annotation.Sign;
import com.my.lfy.utils.JsonResult;
import com.my.lfy.utils.MyEnum;
import com.my.lfy.utils.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * TestController
 *
 * @author lfy
 * @date 2020/7/13
 **/
@Slf4j
@RestController
@RequestMapping("v1/test")
@Api(tags = "【TEST-COMMON】")
public class TestController {

    @Autowired
    private MyFactory myFactory;

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private TestService testService;

    @PostMapping()
    @ApiOperation(value = "test-01")
    public JsonResult test() {
        myFactory.get(MyEnum.TEST.getTest("A")).print();
        return new JsonResult<>();
    }


    @PostMapping("/02")
    @ApiOperation(value = "test-mybatis-list")
    public JsonResult test02() {
        testService.test02();
        return new JsonResult<>();
    }

    @PostMapping("/03")
    @ApiOperation(value = "test-mybatis-object-list")
    public JsonResult test03() {

        TestModel model = new TestModel();
        List<String> tempList = new ArrayList<>();
//        tempList.add("REG_DIRECT_CHARGE");
        model.setCode(tempList);
        return new JsonResult<>(commonMapper.queryListModel(model));
    }

    @PostMapping("/04")
    @ApiOperation(value = "test-retry")
    public JsonResult test04() {
        testService.test04();
        return new JsonResult<>();
    }

    @PostMapping("/excel")
    @ApiOperation(value = "test-excel")
    public void testExcel(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam("filePath") String filePath,
                          @RequestParam("sheetAt") Integer sheetAt,
                          @RequestParam("cellAt") Integer cellAt,
                          @RequestParam("source") String source,
                          @RequestParam("target") String target) {
        testService.testExcel(request, response, filePath, sheetAt, cellAt, source, target);
    }

    @PostMapping("/csv")
    public JsonResult testCsv() {
        return new JsonResult<>(testService.testCsv());
    }

    @PostMapping("/sign")
    @Sign
    public JsonResult testSign(@RequestBody User user) {
        return new JsonResult<>(testService.testSign(user));
    }
}
