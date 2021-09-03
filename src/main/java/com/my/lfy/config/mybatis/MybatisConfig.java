//package com.my.lfy.config.mybatis;
//
//
//import com.my.lfy.config.mybatis.interceptor.CreateTimeInterceptor;
//import com.my.lfy.config.mybatis.interceptor.OptimisticLocker;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 自定义数据类型
// *
// * @author : 杨旭平
// * @date 19-03-13
// */
//@Configuration
//public class MybatisConfig {
//
//
////    @Bean(name = "DataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.master")
////    public DataSource dataSource(){
////        return DataSourceBuilder.create().build();
////    }
//
//    /**
//     * mybatis乐观锁插件
//     */
//    @Bean
//    public OptimisticLocker optimisticLocker(){
//        return new OptimisticLocker();
//    }
//
//    /**
//     * 插入数据时设置createTime值
//     * @return
//     */
//    @Bean
//    public CreateTimeInterceptor insertCreateTime() {
//        return new CreateTimeInterceptor();
//    }
//}
