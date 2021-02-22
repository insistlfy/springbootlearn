package com.my.lfy.api.retrofit.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.my.lfy.api.test.model.TestModel;
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
     * testPostNoParams
     *
     * @param url String
     * @return JsonResult
     */
    @POST
    JsonResult<List<String>> testPostNoParams(@Url String url);

    /**
     * testPostJson
     *
     * @param testModel TestModel
     * @param page      Integer
     * @return JsonResult
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("v1/test/testPostJson")
    JsonResult testPostJson(@Body TestModel testModel, @Query("page") Integer page);

    /**
     * testPostForm TODO
     *
     * @param name
     * @return
     */
    @POST("v1/test/testPostForm")
    @FormUrlEncoded
    JsonResult testPostForm(@Field("name") String name);

    /**
     * testGet
     *
     * @param id   Long
     * @param name String
     * @return JsonResult
     */
    @GET("v1/test/testGet/{id}")
    JsonResult<String> testGet(@Path("id") Long id, @Query("name") String name);

    /**
     * testPut TODO
     *
     * @param id Long
     * @return JsonResult
     */
    @Multipart
    @PUT("/testPut")
    JsonResult<String> testPut(@Part("id") Long id);

}
