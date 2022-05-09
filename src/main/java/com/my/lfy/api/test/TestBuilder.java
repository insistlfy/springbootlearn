package com.my.lfy.api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @FileName: TestBuilder
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/4/26 10:55
 * @Version: 1.0
 * @Company: ©2021东方微银信息技术服务（西安）有限公司
 **/
@Slf4j
public class TestBuilder {

    public static void main(String[] args) {

        User user = new User();
        user.setCreateTime(LocalDateTime.now());
        log.info("user:{}.", JSON.toJSONString(user));

        User user1 = User.builder()
                .name("James")
                .age(24)
                .createTime(LocalDateTime.now())
                .updateTime(null)
                .build();
        log.info("user1:{}.", JSON.toJSONString(user1, SerializerFeature.WriteMapNullValue));

    }
}

@Data
@SuperBuilder
class User extends BaseEntity {

    private static final long serialVersionUID = -7529482750001429375L;
    private String name;

    private Integer age;

    /**
     * 注解@Tolerate解决和@Builder的冲突
     */
    @Tolerate
    public User() {
    }


}

@Data
@SuperBuilder
class BaseEntity implements Serializable {

    private static final long serialVersionUID = -669424557179245211L;
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer page = 1;

    private Integer size = 10;

    @Tolerate
    public BaseEntity() {

    }
}