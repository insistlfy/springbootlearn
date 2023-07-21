package com.my.lfy.api.transaction.mapper;

import com.my.lfy.api.mybatis.model.MybatisTest1Vo;
import com.my.lfy.api.mybatis.model.MybatisTest2Vo;
import com.my.lfy.api.test.model.DicModel;
import com.my.lfy.api.test.model.TestModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;
import java.util.Map;

/**
 * PgCommonMapper
 *
 * @author lfy
 * @date 2020/5/25
 **/
@Mapper
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

    /**
     * 查询医保患者
     *
     * @param cardNoList List<String>
     * @return List<Map < String, Object>>
     */
    List<String> queryPatientInfo(@Param("cardNoList") List<String> cardNoList);


    /**
     * getBookSchedulingSeq
     *
     * @return Long
     */
    Long getBookSchedulingSeq();

    /**
     * getBookMasterSeq
     *
     * @return Long
     */
    Long getBookMasterSeq();

    /**
     * test Cursor
     *
     * @param limit Integer
     * @return Cursor
     */
    @Select("select * from public.dic_stan limit #{limit}")
    Cursor<DicModel> scan(@Param("limit") Integer limit);


    Map<String, Object> test(@Param("productCode") String productCode);

    List<Map<String, Object>> querySubject1(@Param("universityCode") String universityCode,
                                            @Param("subjectCodeList") List<String> subjectCodeList);

    List<Map<String, Object>> querySubject2(List<MybatisTest1Vo> mybatisTest1VoList);

    List<Map<String, Object>> querySubject3(List<MybatisTest2Vo> params);
}
