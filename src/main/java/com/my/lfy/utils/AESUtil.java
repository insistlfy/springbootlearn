package com.my.lfy.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;

/**
 * @FileName: AESUtil
 * @Description: AES-加解密
 * @Author: Lify
 * @CreateDate: 2022/5/19 18:08
 **/
@Slf4j
public final class AESUtil extends AbstractCipher implements Serializable {

    private volatile static AESUtil aesUtil;

    private AESUtil() {
    }

    public static AESUtil getInstance() {
        if (null == aesUtil) {
            synchronized (AESUtil.class) {
                if (null == aesUtil) {
                    aesUtil = new AESUtil();
                }
            }
        }
        return aesUtil;
    }

    @Override
    public String encrypt(String data) {
        check(data);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
            // CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);
            // BASE64做转码。
            return encode(encrypted).trim();
        } catch (Exception e) {
            log.error("EncryptUtils-aes加密失败...", e);
            return null;
        }
    }

    @Override
    public String decrypt(String data) {
        check(data);
        try {
            //先用base64解密
            byte[] encrypted = decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.trim();
        } catch (Exception e) {
            log.error("EncryptUtils-aes解密失败...", e);
            return null;
        }
    }
}
