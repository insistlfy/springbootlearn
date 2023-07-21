package com.my.lfy.api.mybatis.service;

import com.alibaba.fastjson.JSON;
import com.my.lfy.api.mybatis.model.MybatisTest1Vo;
import com.my.lfy.api.mybatis.model.MybatisTest2Vo;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MyBatisService
 *
 * @author lfy
 * @date 2021/2/18
 **/
@Slf4j
@Service
//@RefreshScope
public class MyBatisService {

    @Value("${test.way:test}")
    private String way;

    @Autowired
    private CommonMapper commonMapper;

    public void test01() {

        commonMapper.queryNameById(100022275L);

        log.info("first query successfully...");

        commonMapper.queryNameById(100022275L);
    }

    public void init() {
        Long seq1 = commonMapper.getBookMasterSeq();
        log.info("seq1={}.", seq1);

        Long seq2 = commonMapper.getBookMasterSeq();
        log.info("seq2={}.", seq2);

    }

    public void test03() {
        Map<String, Object> map = commonMapper.test("PER01A0103");
        log.info("data is {}", JSON.toJSONString(map));
    }

    public List<Map<String, Object>> test04(List<MybatisTest1Vo> mybatisTest1VoList) {

        // 学校与学科的关系，同个学校可以有不同的学科，同个学科可以在不同的学校，也就说：学校与学科是多对多的关系
        // 需求：如何查询多个学校下的某些学科

        // 方案一：循环查询
        List<Map<String, Object>> list1 = new ArrayList<>();
        mybatisTest1VoList.forEach(e -> {
            List<Map<String, Object>> list = commonMapper.querySubject1(e.getUniversityCode(), e.getSubjectCodeList());
            if (CollectionUtils.isNotEmpty(list)) {
                list1.addAll(list);
            }
        });
        log.info("方案一>>>>>>循环查询:{}", list1);

        // 方案二：OR拼接
        List<Map<String, Object>> list2 = commonMapper.querySubject2(mybatisTest1VoList);
        log.info("方案二>>>>>>OR拼接:{}", list2);

        // 方案三：行行比较
        List<MybatisTest2Vo> params = new ArrayList<>();
        mybatisTest1VoList.forEach(e -> e.getSubjectCodeList().forEach(s -> params.add(MybatisTest2Vo.builder().universityCode(e.getUniversityCode()).subjectCode(s).build())));
        List<Map<String, Object>> list3 = commonMapper.querySubject3(params);
        log.info("方案三>>>>>>行行比较:{}", list3);
        return list3;
    }

    public String test005() {
        return way;
    }
}
