package com.my.lfy.api.forest.service;

import com.my.lfy.api.forest.client.MyClient;
import com.my.lfy.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ForestService
 *
 * @author lfy
 * @date 2020/7/24
 **/
@Service
public class ForestService {

    @Autowired
    private MyClient myClient;

    public JsonResult test001() {

        return myClient.simplePost();
    }

    public JsonResult test002() {
        return myClient.posto2();
    }
}
