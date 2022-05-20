package com.my.lfy.utils;

import com.my.lfy.exception.ServiceException;
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
public abstract class AbstractCipher implements Serializable {

    public static final String KEY = "hj7x89H$yuBI0456";
    public static final String IV = "NIfb&95GUY86Gfgh";

    /**
     * 加密
     *
     * @param data 明文
     * @return 密码
     */
    public abstract String encrypt(String data);

    /**
     * 解密
     *
     * @param data 密文
     * @return 明文
     */
    public abstract String decrypt(String data);

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
            throw new ServiceException("加/解密值不能为空");
        }
    }
}
