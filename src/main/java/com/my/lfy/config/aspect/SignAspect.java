package com.my.lfy.config.aspect;

import com.my.lfy.config.annotation.Sign;
import com.my.lfy.config.constant.MyConstants;
import com.my.lfy.exception.ServiceException;
import com.my.lfy.config.redis.RedisHelper;
import com.my.lfy.utils.SignUtils;
import com.my.lfy.utils.SyncStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * SignAspect
 *
 * @author lfy
 * @date 2021/2/1
 **/
@Component
@Aspect
@Slf4j
public class SignAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisHelper redisHelper;


    @Around(value = "@annotation(around)")
    public Object process(ProceedingJoinPoint point, Sign around) throws Throwable {

        log.info("start verifySign params ... ");

        // 请求发起的时间
        String timeStamp = request.getHeader("timeStamp");

        //随机数
        String nonce = request.getHeader("nonce");

        //签名
        String sign = request.getHeader("sign");

        if (SyncStringUtils.isAnyBlank(timeStamp, nonce, sign)) {
            log.error("timeStamp={},nonce={},sign={}.", timeStamp, nonce, sign);
            throw new ServiceException("请求头参数不能为空");
        }

        long now = System.currentTimeMillis() / 1000;
        long timeLimit = 120L;

        if (now - Long.parseLong(timeStamp) > timeLimit) {
            throw new ServiceException("请求过期，请重新发起请求");
        }

        //请求参数
        Object[] args = point.getArgs();

        //验签
        if (!SignUtils.verifySign(args[0], sign)) {
            throw new ServiceException("验签失败");
        }

        String redisKey = timeStamp.concat(nonce).concat(sign);
        if (redisHelper.existKey(MyConstants.COMMON_CACHE_SIGN, redisKey)) {
            throw new ServiceException("请求重复了,请30秒后重试");
        } else {
            redisHelper.setObj(MyConstants.COMMON_CACHE_SIGN, redisKey, sign, 30L);
        }

        log.info("verifySign successfully... ");
        return point.proceed();
    }
}
