package com.my.lfy.api.transaction.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * PgCommonMapper
 *
 * @author lfy
 * @date 2020/5/25
 **/
@Repository
public interface PgCommonMapper {

    /**
     * 通过身份证号查询姓名
     *
     * @param patId 患者id
     * @return String
     */
    String queryNameById(@Param("patId") Long patId);

    /**
     * 根据患者id更新患者地址
     *
     * @param address String
     * @param patId   患者id
     */
    void updateAddressById(@Param("address") String address,
                           @Param("patId") Long patId);
}
