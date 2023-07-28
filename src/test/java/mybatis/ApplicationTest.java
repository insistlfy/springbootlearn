package mybatis;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

/**
 * @FileName: ApplicationTest
 * @Description: mybatis-plus自动生成代码
 * @Author: lfy
 * @CreateDate: 2023/7/28 9:42
 * @Version: 1.0
 **/
@SpringBootTest
public class ApplicationTest {

    @Test
    public void testGeneration() {
        // 定义数据库名称
        String databaseName = "test";
        // 定义表名称
        String tableName = "user";
        // 定义数据库名称
        String username = "test";
        // 定义数据库密码
        String password = "test";
        // 定义创作者名称
        String author = "lfy";
        // 定义包名
        String packageName = "com.my.lfy.test";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/" + databaseName + "?&useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai", username, password)
                // 全局配置
                .globalConfig(builder -> builder.author(author)
                        // 指定输出目录
                        .outputDir(System.getProperty("user.dir") + "/src/main/java"))
                // 包名配置
                .packageConfig(builder -> builder.entity("entity") // 实体类包名
                        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                        .parent(packageName)
                        // 控制层包名
                        .controller("controller")
                        // mapper层包名
                        .mapper("mapper")
                        // 生成dto目录 可不用
                        .other("dto")
                        // service层包名
                        .service("service")
                        // service实现类包名
                        .serviceImpl("service.impl")
                        // 自定义mapper.xml文件输出目录
                        .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"))).strategyConfig(builder -> {
                    // 设置要生成的表名
                    builder.addInclude(tableName)
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            // 数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                            .naming(NamingStrategy.underline_to_camel)
                            // 表字段映射实体属性命名规则：默认null，不指定按照naming执行
                            .columnNaming(NamingStrategy.underline_to_camel)
                            // 添加全局主键类型
                            .idType(IdType.INPUT)
                            // 格式化实体名称，%s取消首字母I,
                            .formatFileName("%s")
                            // 开启mapper注解
                            .mapperBuilder().enableMapperAnnotation()
                            // 启用xml文件中的BaseResultMap 生成
//                            .enableBaseResultMap()
                            // 启用xml文件中的BaseColumnList
//                            .enableBaseColumnList()
                            // 格式化Dao类名称
                            .formatMapperFileName("%sMapper")
                            // 格式化xml文件名称
                            .formatXmlFileName("%sMapper")
                            // 格式化 service 接口文件名称
                            .serviceBuilder().formatServiceFileName("%sService")
                            // 格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder()
                            .enableRestStyle();
                }).execute();
    }
}
