package com.my.lfy.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @FileName: Student
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/5/12 11:08
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 2658915263940861768L;

    @ApiModelProperty(value = "年龄")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;
}
