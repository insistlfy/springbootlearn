package com.my.lfy.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MybatisPlusConfig
 * @Description: 配置分页插件
 * @Author: LFY
 * @Created: 2021/9/2 16:22
 * @Versions: V3.0
 * @Company: ©2021东方微银科技（西安）有限公司
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
