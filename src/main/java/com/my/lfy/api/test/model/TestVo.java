package com.my.lfy.api.test.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @FileName: TestVo
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/7/20 9:10
 * @Version: 1.0
 **/
@Data
@Builder
public class TestVo implements Serializable {

    @ExcelProperty(value = "数据类型名称",index = 0)
    private String data_type_name;

    @ExcelProperty(value = "分类路径",index = 1)
    private String class_path;

    @ExcelProperty(value = "分类级别",index = 2)
    private String level_id_name;

    @ExcelProperty(value = "是否敏感",index = 3)
    private String sensitive_name;
}
