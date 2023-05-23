package com.my.lfy.config.mybatis;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;

/**
 * @FileName: MybatisPlusGeneratorConfig
 * @Description: mybatis-plus 代码自动生成配置类
 * @Author: lfy
 * @CreateDate: 2023/5/23 10:02
 * @Version: 1.0
 **/
public class MybatisPlusGeneratorConfig {

    public static void main(String[] args) {
        GeneratorConfig generatorConfig = GeneratorConfig.builder()
                .jdbcUrl("jdbc:mysql://10.1.28.174:3306/dev_cydb_mysql?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&allowMultiQueries=true")
                .userName("dev_cydb_mysql")
                .password("dev_cydb_mysql")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .nameConverter(new NameConverter() {
                    @Override
                    public String entityNameConvert(String tableName) {
                        return NameConverter.super.entityNameConvert(tableName);
                    }

                    @Override
                    public String propertyNameConvert(String fieldName) {
                        return NameConverter.super.propertyNameConvert(fieldName);
                    }

                    @Override
                    public String mapperNameConvert(String tableName) {
                        return NameConverter.super.mapperNameConvert(tableName);
                    }

                    @Override
                    public String mapperXmlNameConvert(String tableName) {
                        return NameConverter.super.mapperXmlNameConvert(tableName);
                    }

                    @Override
                    public String serviceNameConvert(String tableName) {
                        return NameConverter.super.serviceNameConvert(tableName);
                    }

                    @Override
                    public String serviceImplNameConvert(String tableName) {
                        return NameConverter.super.serviceImplNameConvert(tableName);
                    }

                    @Override
                    public String controllerNameConvert(String tableName) {
                        return NameConverter.super.controllerNameConvert(tableName);
                    }

                    @Override
                    public String outputFileNameConvert(String fileType, String tableName) {
                        return NameConverter.super.outputFileNameConvert(fileType, tableName);
                    }
                })
                .basePackage("com.my.lfy.test")
                .port(8889)
                .build();
        MybatisPlusToolsApplication.run(generatorConfig);
    }
}
