package com.my.lfy.api.forest.client;

import com.dtflys.forest.annotation.Request;
import com.my.lfy.utils.JsonResult;

/**
 * MyClient
 *
 * @author lfy
 * @date 2020/7/24
 **/
public interface MyClient {

    @Request(
            url = "http://localhost:8888/lfy/v1/test",
            type = "post",
            dataType = "json")
    JsonResult simplePost();


    @Request(
            url = "http://localhost:8888/lfy/v1/test/02",
            type = "post",
            dataType = "json")
    JsonResult posto2();


}
