package com.my.lfy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

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
@EnableRetry
@ServletComponentScan
@SpringBootApplication
public class LfyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfyApplication.class, args);
    }

}
