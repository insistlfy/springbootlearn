package com.my.lfy.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @FileName: RASUtil
 * @Description: RSA工具类
 * @Author: Lify
 * @CreateDate: 2022/5/19 18:07
 **/
@Slf4j
public final class RASUtil extends AbstractCipher implements Serializable {

    private static final long serialVersionUID = -821503608896159173L;

    private volatile static RASUtil rasUtil;

    private RASUtil() {
    }

    public static RASUtil getInstance() {
        if (null == rasUtil) {
            synchronized (RASUtil.class) {
                if (null == rasUtil) {
                    rasUtil = new RASUtil();
                }
            }
        }
        return rasUtil;
    }

    @Override
    public String encrypt(String... paras) {
        DataKey dataKey = check(paras);
        try {
            RSAPublicKey publicKey = getPublicKey(dataKey.getKey());
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, dataKey.getData().getBytes(StandardCharsets.UTF_8), publicKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + dataKey.getData() + "]时遇到异常", e);
        }
    }

    @Override
    public String decrypt(String... paras) {
        DataKey dataKey = check(paras);
        try {
            RSAPrivateKey privateKey = getPrivateKey(dataKey.getKey());
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(dataKey.getData()), privateKey.getModulus().bitLength()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + dataKey.getKey() + "]时遇到异常", e);
        }
    }


    /**
     * 生成密钥对
     *
     * @param keySize int
     * @return Map
     */
    public static RsaKey createKeys(int keySize) {
        //为RSA算法创建一个KeyPairGenerator对象（KeyPairGenerator，密钥对生成器，用于生成公钥和私钥对）
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[ RSA ]");
        }
        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(keySize);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        return RsaKey.builder()
                .publicKey(Base64.encodeBase64URLSafeString(publicKey.getEncoded()))
                .privateKey(Base64.encodeBase64URLSafeString(privateKey.getEncoded()))
                .build();
    }

    /**
     * 获取公钥
     *
     * @param publicKey String
     * @return RSAPublicKey
     * @throws NoSuchAlgorithmException e
     * @throws InvalidKeySpecException  e
     */
    private RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        return (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
    }

    /**
     * 获取私钥
     *
     * @param privateKey String
     * @return RSAPrivateKey
     * @throws NoSuchAlgorithmException e
     * @throws InvalidKeySpecException  e
     */
    private RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        return (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
    }

    private byte[] rsaSplitCodec(Cipher cipher, int opMode, byte[] data, int keySize) {
        int maxBlock;
        if (opMode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (data.length > offSet) {
                if (data.length - offSet > maxBlock) {
                    buff = cipher.doFinal(data, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(data, offSet, data.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultData = out.toByteArray();
        IOUtils.closeQuietly(out);
        return resultData;
    }

    @Data
    @Builder
    public static class RsaKey implements Serializable {

        private static final long serialVersionUID = -9109939926197733102L;

        @ApiModelProperty(value = "公钥")
        private String publicKey;

        @ApiModelProperty(value = "私钥")
        private String privateKey;

        @Tolerate
        public RsaKey() {
        }

    }
}
