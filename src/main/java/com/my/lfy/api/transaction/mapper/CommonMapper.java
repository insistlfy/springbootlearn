package com.my.lfy.api.transaction.mapper;

import com.my.lfy.api.test.model.TestModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PgCommonMapper
 *
 * @author lfy
 * @date 2020/5/25
 **/
@Repository
public interface CommonMapper {

    /**
     * 通过身份证号查询姓名
     *
     * @param patId 患者id
     * @return String
     */
    String queryNameById(@Param("patId") Long patId);

    /**
     * 查询系统参数值
     *
     * @param paraCode
     * @return String
     */
    String queryParaValue(@Param("paraCode") String paraCode);

    /**
     * 根据患者id更新患者地址
     *
     * @param address String
     * @param patId   患者id
     */
    void updateAddressById(@Param("address") String address,
                           @Param("patId") Long patId);

    /**
     * 测试MyBatis之List
     *
     * @param params List
     * @return List
     */
    List<String> queryList(@Param("params") List<String> params);

    /**
     * 测试MyBatis之List-model
     *
     * @param model TestModel
     * @return List
     */
    List<String> queryListModel(@Param("model") TestModel model);
}
