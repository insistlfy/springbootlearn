package com.my.lfy.api.transaction.service;

import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * TransactionServiceImpl
 *
 * @author lfy
 * @date 2020/5/25
 **/
@Slf4j
@Service
public class ServiceA {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private ServiceB serviceB;

//    @Transactional(propagation = Propagation.REQUIRED)
    public void testReadonly() {

        log.info("STEP1==============> 开始第一次查询..........");
        String first = commonMapper.queryNameById(100019466L);
        log.info("first===============>{}.", first);

        log.info("STEP1==============> 开始第二次查询..........");
        String second = commonMapper.queryNameById(100019466L);
        log.info("second===============>{}.", second);

        log.info("STEP2==============> 开始第三次查询..........");
        String paraValue = commonMapper.queryParaValue("HIS_URL");
        log.info("paraValue===============>{}.", paraValue);

        update01();

        funcD();

        int i = 1 / 0;

    }

    @Transactional(readOnly = true)
    public void test002() {
        funcA();
        try {
            funcC();
        } catch (Exception e) {
            log.error("funcC======>执行失败");
        }
    }

    @Transactional
    public void test003() {
        funcB();
        funcC();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void checkUnfreezeCharge() {
        commonMapper.queryNameById(100019466L);
        log.info("==========>校验");
        handleCharged();
    }

    void handleCharged() {
        log.info("handleCharged==============> 开始执行..........");
        commonMapper.queryParaValue("HIS_URL");
        chargedPrescription();
    }

    void chargedPrescription() {
        log.info("chargedPrescription==============> 开始执行..........");
        log.info("模拟存储过程执行==============> 开始执行..........");
//        pgCommonMapper.updateAddressById("update01", 100006302L);
        try {
            serviceB.methodB5();
        } catch (Exception e) {
            log.error("methodB5==========>执行失败.", e);
        }
    }


    private void update01() {
        log.info("update01==============> 开始执行..........");
        commonMapper.updateAddressById("update01", 100006302L);
        log.info("update01==============> 执行完成..........");
    }

    private void funcA() {
        log.info("funcA==============> 开始执行..........");
        commonMapper.updateAddressById("funcA", 100006276L);
        log.info("funcA==============> 执行完成..........");
    }

    private void funcB() {
        try {
            log.info("funcB==============> 开始执行..........");
            commonMapper.updateAddressById("funcB", 100019606L);
            log.info("funcB==============> 执行完成..........");
        } catch (Exception e) {
            log.error("funcB==============> 执行失败");
        }
    }

    private void funcC() {
        log.info("funcC==============> 开始执行..........");
        if (null == commonMapper.queryNameById(0L)) {
            throw new ServiceException("未查询到信息,无需更新");
        }
        commonMapper.updateAddressById("funcC", 100019606L);
        log.info("funcB==============> 执行完成..........");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    void funcD() {
        log.info("funcD==============>..........");
        commonMapper.updateAddressById("funcD", 100019606L);
        log.info("funcD===============>.");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void testPropagation() {
        log.info("serviceA==============> 开始第一次查询..........");
        String first = commonMapper.queryNameById(100019466L);
        log.info("serviceAFirst===============>{}.", first);

        log.info("serviceB============>methodB1====>开始");
        serviceB.methodB1();

        log.info("serviceB============>methodB2====>开始");
        serviceB.methodB2();

        log.info("serviceB============>methodB3====>开始");
        serviceB.methodB3();

        log.info("serviceB============>methodB4====>开始");
        serviceB.methodB4();

    }
}
