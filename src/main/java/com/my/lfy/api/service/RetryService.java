package com.my.lfy.api.service;

import com.my.lfy.exception.ServiceException;
import com.my.lfy.utils.SyncStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * RetryService
 *
 * @author lfy
 * @date 2020/4/14
 **/
@Slf4j
@Service
public class RetryService {

    @Retryable(value = Exception.class, maxAttempts = 2, backoff = @Backoff(delay = 5000, multiplier = 1))
    public boolean test(String str) {
        log.info("starting ...");
        if (SyncStringUtils.startWithChar(str)) {
            throw new ServiceException("该字符串不能已字符串开头...");
        }
        return true;
    }

    @Recover
    public boolean callBack(Exception e) {
        log.info("回调方法执行...");
        log.error("回调=======>", e);
        return false;
    }

}
