package com.my.lfy.api.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.my.lfy.config.mybatis.BaseModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * test
 * </p>
 *
 * @author Lify
 * @since 2021-09-02
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MYBATIS_TEST")
public class MybatisTest extends BaseModel {

    private static final long serialVersionUID = 1L;


    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private String id;
    /**
     * 年龄
     */
    @TableField("AGE")
    private Integer age;

    /**
     * 姓名
     */
    @TableField("NAME")
    private String name;
}
