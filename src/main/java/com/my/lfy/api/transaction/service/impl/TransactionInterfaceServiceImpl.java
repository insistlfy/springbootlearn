package com.my.lfy.api.transaction.service.impl;

import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.api.transaction.service.TransactionInterfaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TransactionInterfaceServiceImpl
 *
 * @author lfy
 * @date 2020/6/17
 **/
@Slf4j
@Service
public class TransactionInterfaceServiceImpl implements TransactionInterfaceService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    @Transactional(readOnly = true)
    public void testInterface() {
        log.info("update01==============> 开始执行..........");
        commonMapper.updateAddressById("update01", 100006302L);
        log.info("update01==============> 执行完成..........");
    }
}
