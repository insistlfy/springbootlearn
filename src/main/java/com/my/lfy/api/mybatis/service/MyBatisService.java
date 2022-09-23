package com.my.lfy.api.mybatis.service;

import com.alibaba.fastjson.JSON;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * MyBatisService
 *
 * @author lfy
 * @date 2021/2/18
 **/
@Slf4j
@Service
public class MyBatisService {

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
}
