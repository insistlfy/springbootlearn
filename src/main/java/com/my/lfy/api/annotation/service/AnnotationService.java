package com.my.lfy.api.annotation.service;

import com.my.lfy.api.annotation.model.BaseInfo;
import com.my.lfy.api.annotation.model.ResultVo;
import com.my.lfy.config.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * AnnotationService
 *
 * @author lfy
 * @date 2020/7/14
 **/
@Slf4j
@Service
public class AnnotationService {

    @MyAnnotation
    public ResultVo test01(String sid, BaseInfo baseInfo) {

        ResultVo resultVo = ResultVo.builder()
                .name("James")
                .sex("男")
                .weight("60")
                .operCode(baseInfo.getOperCode())
                .build();
        String operCode = baseInfo.getOperCode();
        log.info("operCode====>{}.", operCode);
        return resultVo;
    }
}
