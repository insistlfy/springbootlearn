package com.my.lfy.api.annotation.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 * ResultVo
 *
 * @author lfy
 * @date 2020/7/15
 **/
@Setter
@Getter
@Builder
@ToString
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo implements Serializable {

    private static final long serialVersionUID = 155873178516913288L;

    @ApiModelProperty(value = "sex")
    private String sex;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "weight")
    private String weight;

    @ApiModelProperty(value = "operCode")
    private String operCode;
}
