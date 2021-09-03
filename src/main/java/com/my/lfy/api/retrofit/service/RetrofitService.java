//package com.my.lfy.api.retrofit.service;
//
//import com.my.lfy.api.retrofit.api.HttpApi;
//import com.my.lfy.api.test.model.TestModel;
//import com.my.lfy.utils.JsonResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * RetrofitService
// *
// * @author lfy
// * @date 2021/2/18
// **/
//@Slf4j
//@Service
//public class RetrofitService {
//
//    @Autowired
//    private HttpApi httpApi;
//
//    public void testPostNoParams() {
//        JsonResult<List<String>> result = httpApi.testPostNoParams("v1/test/testPostNoParams");
//        log.info("testPostNoParams is successful");
//    }
//
//    public void testPostJson() {
//        TestModel model = TestModel.builder()
//                .name("James")
//                .age(30)
//                .build();
//        httpApi.testPostJson(model, 10);
//        log.info("testPostJson is successful");
//    }
//
//    public void testGet() {
//        JsonResult<String> result = httpApi.testGet(123456L, "James");
//        log.info("testGet is successful");
//    }
//
//    public void testPostForm() {
//        httpApi.testPostForm("name");
//        log.info("testPut is successful");
//    }
//}
