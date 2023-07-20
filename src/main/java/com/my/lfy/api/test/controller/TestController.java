package com.my.lfy.api.test.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.my.lfy.api.test.model.TestModel;
import com.my.lfy.api.test.model.TestVo;
import com.my.lfy.api.test.service.ITestService;
import com.my.lfy.api.test.service.MyFactory;
import com.my.lfy.api.test.service.TestService;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.config.annotation.OperLog;
import com.my.lfy.config.annotation.Sign;
import com.my.lfy.exception.BaseExceptionMsg;
import com.my.lfy.utils.JsonResult;
import com.my.lfy.utils.MyEnum;
import com.my.lfy.utils.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private List<ITestService> testServiceList;


    @PostMapping("/bean")
    @ApiOperation(value = "test-bean")
    public JsonResult testBean() {
        testServiceList.forEach(e -> System.out.println(e));
        return new JsonResult<>();
    }

    @PostMapping()
    @ApiOperation(value = "test-01")
    public JsonResult test() {
        myFactory.get(MyEnum.TEST.getTest("A")).print();
        return new JsonResult<>();
    }


    @PostMapping("/02")
    @ApiOperation(value = "test-mybatis-list")
    public JsonResult test02() {
        return new JsonResult<>(testService.test02());
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


    @OperLog(operModel = "test", operDesc = "test", operType = com.my.lfy.config.constant.MyEnum.OperType.OTHER)
    @PostMapping("/test/log")
    public JsonResult testLog() {
        return new JsonResult(BaseExceptionMsg.EXECUTE_OK);
    }

    @PostMapping("/test/mybatis")
    public JsonResult testMybatis(@RequestParam("limit") Integer limit) {
        return new JsonResult(testService.testMybatis(limit));
    }

    /*-------------------------------Test Retrofit--------------------------------------------*/

    @PostMapping("/testPostNoParams")
    @ApiOperation(value = "test-Retrofit-testPostNoParams")
    public JsonResult testPostNoParams() {
        return new JsonResult<>(testService.test02());
    }

    @PostMapping("/testPostJson")
    @ApiOperation(value = "test-Retrofit-testPostJson")
    public JsonResult testPostJson(@RequestBody TestModel testModel, @RequestParam("page") Integer page) {
        testService.testPostJson(testModel, page);
        return new JsonResult<>();
    }

    @GetMapping("/testGet/{id}")
    @ApiOperation(value = "test-Retrofit-testGet")
    public JsonResult testGet(@PathVariable("id") Long id, @RequestParam("name") String name) {
        testService.testGet(id, name);
        return new JsonResult<>();
    }

    @PutMapping("/testPut/{id}")
    @ApiOperation(value = "test-Retrofit-testPut")
    public JsonResult testPut(@PathVariable("id") Long id) {
        testService.testPut(id);
        return new JsonResult<>();
    }

    @PutMapping("/arthas")
    @ApiOperation(value = "test-arthas")
    public JsonResult testArthas() {
        log.info("test arthas...");
        return new JsonResult<>();
    }

    @PostMapping(value = "/test")
    public void test(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        try {
            response.setContentType("application/vnd.ms-excel");
            String fileName = URLEncoder.encode("分级分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<TestVo> responseResult = getData();
            EasyExcel.write(response.getOutputStream(), TestVo.class).autoCloseStream(Boolean.FALSE).sheet("分级分类")
                    .doWrite(responseResult);
        } catch (Exception e) {
            response.reset();
            response.setContentType("application/json");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "200");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }

    private static List<TestVo> getData() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "session=MTY4OTgxNjI0MHxEdi1CQkFFQ180SUFBUkFCRUFBQV81bl9nZ0FFQm5OMGNtbHVad3dHQUFSMWRXbGtCbk4wY21sdVp3d21BQ1F3TURRNFlXRTBNaTFrTW1OaExUUmlNVEF0WWpNd1ppMDJOVGhpTnpVMFlXTmtZbUVHYzNSeWFXNW5EQVlBQkhKdmJHVUZhVzUwTmpRRUFnQUNCbk4wY21sdVp3d0lBQVoxYzJWeVNXUUZhVzUwTmpRRUFnQUNCbk4wY21sdVp3d0tBQWgxYzJWeVRtRnRaUVp6ZEhKcGJtY01Cd0FGWVdSdGFXND186DYrZRB2AE2rbvwcFqBVwUNPiChV5iiQgAp6DoJeQoc=");
        headers.put("Sign-Time", "1689816249937");
        headers.put("Sign-Txt", "4046d43c3d1c322fb987e857eaf468c6");
        String body = HttpUtil.createGet("http://10.1.28.155:88/dcgapiserver/api/sort/data/page/list?page=1&size=300&data_class_id&data_type_name&level_id&template_id=1667980990145")
                .addHeaders(headers)
                .execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);

        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("data");

        List<JSONObject> jsonObjectList = new ArrayList<>();
        jsonArray.forEach(e -> jsonObjectList.add((JSONObject) e));
        return jsonObjectList.stream().map(s -> TestVo.builder()
                .class_path(s.getString("class_path"))
                .level_id_name(s.getString("level_id_name"))
                .data_type_name(s.getString("data_type_name"))
                .sensitive_name(s.getString("sensitive_name"))
                .build()).collect(Collectors.toList());
    }
}
