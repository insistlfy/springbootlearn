package com.my.lfy.api.retrofit.service;

import com.my.lfy.api.retrofit.api.HttpApi;
import com.my.lfy.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RetrofitService
 *
 * @author lfy
 * @date 2021/2/18
 **/
@Slf4j
@Service
public class RetrofitService {

    @Autowired
    private HttpApi httpApi;

    public void test01() {
        JsonResult<List<String>> result = httpApi.test02("v1/test/02");
        System.out.println(result);
    }

}
