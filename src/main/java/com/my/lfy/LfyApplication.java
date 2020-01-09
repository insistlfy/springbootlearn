package com.my.lfy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * ActionAspect
 *
 * @author Lifuyuan
 * @date 20-1-9
 **/
@Slf4j
@EnableCaching
@ServletComponentScan
@SpringBootApplication
public class LfyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LfyApplication.class, args);
    }

}
