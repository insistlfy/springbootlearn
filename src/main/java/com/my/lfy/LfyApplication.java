package com.my.lfy;

import com.dtflys.forest.annotation.ForestScan;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * LfyApplication
 *
 * @author Lifuyuan
 * @date 20-1-9
 * 描述 :
 * RefreshScope //自动刷新配置
 **/
@Slf4j
//@RefreshScope
@EnableCaching
//@EnableScheduling
@EnableRetry
@ServletComponentScan
@ForestScan(basePackages = "com.my.lfy.api.forest.client")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.my.lfy.api.transaction.mapper"})
public class LfyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfyApplication.class, args);
    }

}
