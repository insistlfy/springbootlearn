package com.my.lfy.datasource;



import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.my.lfy.config.mybatis.interceptor.CreateTimeInterceptor;
import com.my.lfy.config.mybatis.interceptor.OptimisticLocker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * PgDatasourceConfig
 *
 * @author Lifuyuan
 * @date 19-3-20
 **/
@Slf4j
@Primary
@Configuration
@SuppressWarnings("all")
@MapperScan(basePackages = {"cn.swifthealth.*.api.*.mapper", "cn.swifthealth.*.common.mapper", "cn.swifthealth.api.*" +
        ".mapper", "cn.swifthealth.sync.api.common.*.mapper"}, sqlSessionFactoryRef = "pgSqlSessionFactory")
public class PgDatasourceConfig {

    private static final String PROD = "prod";

    @Value("${spring.profiles.active}")
    private String activeFlag;

    @Value("${data.producer:null}")
    private String producer;

    @Value("${data.addproducer:false}")
    private Boolean addProducer;

    @Value("${data.interceptor:false}")
    private Boolean interceptor;

    @Primary
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource pgDataSource() {
        log.info("pgDataSource 装载......");
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "pgSqlSessionFactory")
    public SqlSessionFactory pgSqlSessionFactory(@Qualifier("pgDataSource") DataSource pgDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(pgDataSource);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Set<Resource> sets = new LinkedHashSet<>(16);
        sets.addAll(Arrays.asList(resolver.getResources("classpath*:mapper/*/*.xml")));
        sets.addAll(Arrays.asList(resolver.getResources("classpath*:config/mapper/*/*.xml")));
        factoryBean.setMapperLocations(sets.toArray(new Resource[0]));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.addInterceptor(new OptimisticLocker());
        configuration.addInterceptor(new CreateTimeInterceptor(producer, addProducer, interceptor));
        configuration.addInterceptor(new CreateTimeInterceptor());
        //打印sql
        if (!activeFlag.contains(PROD)) {
            configuration.setLogImpl(StdOutImpl.class);
        }
        //开启mybatis驼峰式命名规则自动转换
        configuration.setMapUnderscoreToCamelCase(true);
        //使全局的映射器启用或禁用缓存
        configuration.setCacheEnabled(true);
        //全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载
        configuration.setLazyLoadingEnabled(true);

        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeHandlersPackage("cn.swifthealth.core.mybatis");
        factoryBean.setVfs(SpringBootVFS.class);
        return factoryBean.getObject();
    }

    @Primary
    @Bean(name = "pgTransactionManager")
    public DataSourceTransactionManager pgTransactionManager(@Qualifier("pgDataSource") DataSource pgDataSource) {
        return new DataSourceTransactionManager(pgDataSource);
    }

    @Primary
    @Bean(name = "pgSqlSessionTemplate")
    public SqlSessionTemplate pgSqlSessionTemplate(@Qualifier("pgSqlSessionFactory") SqlSessionFactory pgSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(pgSqlSessionFactory);
    }

}
