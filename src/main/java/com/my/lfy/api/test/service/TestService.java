package com.my.lfy.api.test.service;

import com.my.lfy.api.retry.service.RetryService;
import com.my.lfy.api.springtask.SpringTaskConfig;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TestService
 *
 * @author lfy
 * @date 2020/7/29
 **/
@Slf4j
@Service
public class TestService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private RetryService retryService;

    @Autowired
    private SpringTaskConfig springTaskConfig;

    public List<String> test02() {
        List<String> params = new ArrayList<>();
        params.add("REG_REFUSE_REASON");
        return commonMapper.queryList(params);
    }

    public boolean test04() {

        log.info("test-retry...");
        springTaskConfig.executor().execute(() -> retryService.test("sad"));

        springTaskConfig.executor().execute(() -> retryService.test01("sad"));

        log.info("test01---end");

        test02();
        return true;
    }
}
