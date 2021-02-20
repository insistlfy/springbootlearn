package com.my.lfy.api.retrofit.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.my.lfy.utils.JsonResult;
import retrofit2.http.*;

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

    /**
     * test03
     *
     * @param name String
     * @return JsonResult
     */
    @GET
    JsonResult<String> test03(@Query("name") String name);

    /**
     * test03
     *
     * @param name String
     * @return JsonResult
     */
    @PUT
    JsonResult<String> test04(@Query("name") String name);

}
