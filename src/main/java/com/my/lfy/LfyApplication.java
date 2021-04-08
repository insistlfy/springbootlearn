package com.my.lfy;

import com.dtflys.forest.annotation.ForestScan;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * LfyApplication
 *
 * @author Lifuyuan
 * @date 20-1-9
 * 描述 :
 * ① RefreshScope ： 自动刷新配置
 * ② @EnableAsync : 开启Spring的异步功能
 **/
@Slf4j
//@RefreshScope
@EnableCaching
//@EnableScheduling
@EnableAsync
@EnableRetry
@ServletComponentScan
@ForestScan(basePackages = "com.my.lfy.api.forest.client")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.my.lfy.api.transaction.mapper"})
@RetrofitScan("com.my.lfy.api.retrofit.api")
public class LfyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfyApplication.class, args);
    }

}
