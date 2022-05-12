package com.my.lfy.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    private static final long serialVersionUID = 2159651044987217615L;

    @ApiModelProperty(value = "名称")
    private String userName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "类型 1：工人，2：农民，3：学生")
    private String type;

    @ApiModelProperty(value = "扩展参数")
    private Map<String, Object> extend;
}
