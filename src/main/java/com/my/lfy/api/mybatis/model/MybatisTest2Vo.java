package com.my.lfy.api.mybatis.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @FileName: MybatisTest1Vo
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/7/21 10:26
 * @Version: 1.0
 **/
@Data
@Builder
public class MybatisTest2Vo implements Serializable {
    private static final long serialVersionUID = -683287357164570107L;

    private String universityCode;

    private String subjectCode;
}
