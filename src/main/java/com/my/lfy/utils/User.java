package com.my.lfy.utils;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * User
 *
 * @author lfy
 * @date 2021/2/1
 **/
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class User implements Serializable {

    private String userName;

    private String sex;

    private Integer age;

    private Map<String, Object> extend;
}
