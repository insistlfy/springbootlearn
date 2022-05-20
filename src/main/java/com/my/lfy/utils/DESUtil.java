package com.my.lfy.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @FileName: DESUtil
 * @Description: DES-加解密
 * @Author: Lify
 * @CreateDate: 2022/5/19 18:07
 **/
@Slf4j
public final class DESUtil extends AbstractCipher implements Serializable {

    private volatile static DESUtil desUtil;

    private DESUtil() {

    }

    public static DESUtil getInstance() {
        if (null == desUtil) {
            synchronized (DESUtil.class) {
                if (null == desUtil) {
                    desUtil = new DESUtil();
                }
            }
        }
        return desUtil;
    }

    @Override
    public String encrypt(String data) {
        check(data);
        try {
            SecretKeySpec key = getSecretKeySpec();
            // 创建密码器
            Cipher cipher = Cipher.getInstance("DES");
            byte[] byteContent = data.getBytes(StandardCharsets.UTF_8);
            // 初始化
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return encode(result).trim();
        } catch (Exception e) {
            log.error("EncryptUtils-des加密失败...", e);
            return null;
        }
    }

    @Override
    public String decrypt(String data) {
        check(data);
        try {
            //先用base64解密
            byte[] encrypted = decode(data);
            SecretKeySpec key = getSecretKeySpec();
            // 创建密码器
            Cipher cipher = Cipher.getInstance("DES");
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 解密
            byte[] result = cipher.doFinal(encrypted);
            return new String(result).trim();
        } catch (Exception e) {
            log.error("EncryptUtils-des解密失败...", e);
            return null;
        }
    }

    private SecretKeySpec getSecretKeySpec() throws NoSuchAlgorithmException {
        KeyGenerator kGen = KeyGenerator.getInstance("DES");
        kGen.init(56, new SecureRandom(KEY.getBytes()));
        SecretKey secretKey = kGen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        return new SecretKeySpec(enCodeFormat, "DES");
    }

}
