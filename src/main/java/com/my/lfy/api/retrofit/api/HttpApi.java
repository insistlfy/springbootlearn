package com.my.lfy.api.retrofit.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.OkHttpClientBuilder;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.my.lfy.utils.JsonResult;
import okhttp3.OkHttpClient;
import retrofit2.http.POST;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * HttpApi
 *
 * @author lfy
 * @date 2021/2/18
 **/
@RetrofitClient(baseUrl = "",poolName = "test1")
public interface HttpApi {

    @OkHttpClientBuilder
    static OkHttpClient.Builder okhttpClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS);

    }

    /**
     * test02
     *
     * @return JsonResult
     */
    @POST("v1/test/02")
    JsonResult<List<String>> test02();
}
