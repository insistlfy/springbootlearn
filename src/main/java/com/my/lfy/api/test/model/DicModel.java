package com.my.lfy.api.test.model;

import lombok.Data;

import java.io.Serializable;

/**
 * DicModel
 *
 * @author lfy
 * @date 2021/2/7
 **/
@Data
public class DicModel implements Serializable {

    private String dicCode;

    private String code;

    private String name;

    private String keyword;
}
