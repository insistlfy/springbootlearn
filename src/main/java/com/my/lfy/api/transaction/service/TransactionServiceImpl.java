package com.my.lfy.api.transaction.service;

import com.my.lfy.api.transaction.mapper.PgCommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TransactionServiceImpl
 *
 * @author lfy
 * @date 2020/5/25
 **/
@Slf4j
@Service
public class TransactionServiceImpl {

    @Autowired
    private PgCommonMapper pgCommonMapper;

    public void test001(Long patId, String address) {

        log.info("STEP1==============> 开始查询..........");
        String name = pgCommonMapper.queryNameById(patId);
        log.info("name===============>{}.", name);

        log.info("STEP2==============> 开始更新..........");
        pgCommonMapper.updateAddressById(address, patId);

        String afterName = pgCommonMapper.queryNameById(patId);
        log.info("afterName===============>{}.", afterName);

    }
}
