package com.my.lfy.api.transaction.service;

import com.my.lfy.api.transaction.mapper.PgCommonMapper;
import com.my.lfy.exception.ServiceException;
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

    public void test001() {

        log.info("STEP1==============> 开始查询..........");
        String name = pgCommonMapper.queryNameById(100019466L);
        log.info("name===============>{}.", name);

        log.info("STEP2==============> 开始更新..........");
        pgCommonMapper.updateAddressById("test001", 100019466L);

        String afterName = pgCommonMapper.queryNameById(100019466L);
        log.info("afterName===============>{}.", afterName);

        try {
            funcC();
        } catch (Exception e) {
            log.error("funcC============>执行失败");
        }

    }


    private void update01() {
        log.info("update01==============> 开始执行..........");
        pgCommonMapper.updateAddressById("update01", 100006302L);
        log.info("update01==============> 执行完成..........");
    }

    private void funcA() {
        log.info("funcA==============> 开始执行..........");
        pgCommonMapper.updateAddressById("funcA", 100006276L);
        log.info("funcA==============> 执行完成..........");
    }

    private void funcB() {
        try {
            log.info("funcB==============> 开始执行..........");
            pgCommonMapper.updateAddressById("funcB", 100019606L);
            int i = 1 / 0;
            log.info("funcB==============> 执行完成..........");
        } catch (Exception e) {
            log.error("funcB==============> 执行失败");
        }
    }

    private void funcC() {
        log.info("funcC==============> 开始执行..........");
        if (null == pgCommonMapper.queryNameById(0L)) {
            throw new ServiceException("未查询到信息,无需更新");
        }
        pgCommonMapper.updateAddressById("funcC", 100019606L);
        log.info("funcB==============> 执行完成..........");

    }
}
