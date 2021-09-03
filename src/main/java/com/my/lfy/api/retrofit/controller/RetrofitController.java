//package com.my.lfy.api.retrofit.controller;
//
//import com.my.lfy.api.retrofit.service.RetrofitService;
//import com.my.lfy.utils.JsonResult;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * RetrofitController
// *
// * @author lfy
// * @date 2021/2/18
// **/
//@Slf4j
//@RestController
//@RequestMapping("v1/retrofit/")
//@Api(tags = "【TEST-RETROFIT】")
//public class RetrofitController {
//
//    @Autowired
//    private RetrofitService retrofitService;
//
//    @PostMapping("testPostNoParams")
//    public JsonResult testPostNoParams() {
//        retrofitService.testPostNoParams();
//        return new JsonResult<>();
//    }
//
//    @PostMapping("testPostJson")
//    public JsonResult testPostJson() {
//        retrofitService.testPostJson();
//        return new JsonResult<>();
//    }
//
//    @Deprecated
//    @PostMapping("testPostForm")
//    public JsonResult testPostForm() {
//        retrofitService.testPostForm();
//        return new JsonResult<>();
//    }
//
//    @PostMapping("testGet")
//    public JsonResult testGet() {
//        retrofitService.testGet();
//        return new JsonResult<>();
//    }
//}
