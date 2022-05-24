package com.my.lfy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.my.lfy.exception.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Base64;

/**
 * @FileName: AbstractCipher
 * @Description: 密码抽象类，统一定义基础接口和变量
 * @Author: Lify
 * @CreateDate: 2022/5/19 18:10
 **/
@Slf4j
public abstract class AbstractCipher {

    /**
     * 加密算法类型
     */
    public static final String DES = "DES";
    public static final String AES = "AES";
    public static final String RSA = "RSA";
    /**
     * 对称加密KEY
     */
    public static final String KEY = "hj7x89H$yuBI0456";

    /**
     * 对称加密偏移量
     */
    public static final String IV = "NIfb&95GUY86Gfgh";

    /**
     * 非对称加密参数长度
     */
    private static final Integer LENGTH = 2;

    /**
     * 密钥大小
     */
    public static final Integer KEY_SIZE = 1024;

    /**
     * 加密
     *
     * @param paras 明文
     * @return 密码
     */
    public abstract String encrypt(String... paras);

    /**
     * 解密
     *
     * @param paras 密文
     * @return 明文
     */
    public abstract String decrypt(String... paras);

    /**
     * 解码
     *
     * @param str String
     * @return String
     */
    public byte[] decode(String str) {
        return Base64.getDecoder().decode(str);
    }

    /**
     * 编码
     *
     * @param byteArray byte[]
     * @return String
     */
    public String encode(byte[] byteArray) {
        return new String(Base64.getEncoder().encode(byteArray));
    }

    /**
     * 参数校验
     *
     * @param data String
     */
    public void check(String data) {
        if (StringUtils.isBlank(data)) {
            log.warn("加/解密值不能为空，data:{}.", data);
            throw new ServiceException("加/解密值不能为空...");
        }
    }

    public DataKey check(String... paras) {
        if (null == paras || LENGTH != paras.length) {
            log.warn("非对称加密参数为：{}。", JSON.toJSONString(paras, SerializerFeature.WriteMapNullValue));
            throw new ServiceException("非对称加密参数传入有误，请传入加/解密值及公钥...");
        }
        return DataKey.builder()
                .data(paras[0])
                .key(paras[1])
                .build();
    }

    @Data
    @Builder
    public static class DataKey implements Serializable {

        private static final long serialVersionUID = -2782629446447313414L;

        @ApiModelProperty(value = "加解密数据")
        private String data;

        @ApiModelProperty(value = "密钥")
        private String key;

        @Tolerate //使用@Builder时，没有生成无参构造器。在进行json序列化时会报错。使用此注解解决冲突
        public DataKey() {
        }
    }
}
