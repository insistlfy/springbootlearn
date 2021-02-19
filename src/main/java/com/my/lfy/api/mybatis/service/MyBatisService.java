package com.my.lfy.api.mybatis.service;

import com.my.lfy.api.transaction.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
