package com.my.lfy.api.retrofit.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.my.lfy.utils.JsonResult;
import retrofit2.http.POST;
import retrofit2.http.Url;

import java.util.List;

/**
 * HttpApi
 *
 * @author lfy
 * @date 2021/2/18
 **/
@RetrofitClient(baseUrl = "${retrofit.base-url}", poolName = "test1")
public interface HttpApi {

    /**
     * test02
     *
     * @param url String
     * @return JsonResult
     */
    @POST
    JsonResult<List<String>> test02(@Url String url);
}
