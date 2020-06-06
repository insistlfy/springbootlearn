package com.my.lfy.api.transaction.service;

import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ServiceB
 *
 * @author lfy
 * @date 2020/5/26
 **/
@Slf4j
@Service
public class ServiceB {

    @Autowired
    private CommonMapper commonMapper;

    public void methodB1() {
        log.info("methodB1=========>开始");
        commonMapper.queryNameById(100006302L);
        log.info("methodB1=========>结束");
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    public void methodB2() {
        log.info("methodB2=========>开始");
        commonMapper.queryParaValue("HIS_URL");
        log.info("methodB2=========>结束");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void methodB3() {
        log.info("methodB3=========>开始");
        commonMapper.updateAddressById("methodB3", 100006276L);
        log.info("methodB3=========>结束");
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void methodB4() {
        log.info("methodB4=========>开始");
        commonMapper.updateAddressById("methodB4", 100006276L);
        log.info("methodB4=========>结束");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void methodB5() {
        log.info("methodB5==============> 开始执行..........");
        if (null == commonMapper.queryNameById(0L)) {
            throw new ServiceException("未查询到信息,无需更新");
        }
        commonMapper.updateAddressById("methodB5", 100019606L);
        log.info("methodB5==============> 执行完成..........");
    }
}
