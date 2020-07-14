package com.my.lfy.api.annotation.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

/**
 * BaseInfo
 *
 * @author lfy
 * @date 2020/7/14
 **/
@Setter
@Getter
@Builder
@ToString
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class BaseInfo implements Serializable {

    private static final long serialVersionUID = 5885380680261395220L;

    @ApiModelProperty(value = "操作员")
    private String operCode;

    @ApiModelProperty(value = "医院id")
    private String hosId;

    @ApiModelProperty(value = "扩展参数")
    private Map<String, Object> params;


}
