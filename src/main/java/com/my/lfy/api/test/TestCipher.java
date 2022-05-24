package com.my.lfy.api.test;

import com.my.lfy.utils.AESUtil;
import com.my.lfy.utils.DESUtil;
import com.my.lfy.utils.RASUtil;
import org.springframework.util.DigestUtils;

import static com.my.lfy.utils.AbstractCipher.KEY_SIZE;

/**
 * @FileName: TestCipher
 * @Description: 加密算法测试类
 * @Author: Lify
 * @CreateDate: 2022/5/20 14:05
 **/
public class TestCipher {

    public static void main(String[] args) {
        String data = "LFY";
        System.out.println("【加密前:】" + data);
        System.out.println("【EncryptUtils-MD5:】" + DigestUtils.md5DigestAsHex(data.getBytes()));
        System.out.println("========================================================");


        String des = DESUtil.getInstance().encrypt(data);
        System.out.println("【DES-加密后:】" + des);
        System.out.println("【DES-解密后:】" + DESUtil.getInstance().decrypt(des));
        System.out.println("========================================================");


        String aes = AESUtil.getInstance().encrypt(data);
        System.out.println("【AES-加密后:】" + aes);
        System.out.println("【AES-解密后:】" + AESUtil.getInstance().decrypt(aes));
        System.out.println("========================================================");


        RASUtil.RsaKey rsaKey = RASUtil.createKeys(KEY_SIZE);
        System.out.println("publicKey: " + rsaKey.getPublicKey());
        System.out.println("privateKey: " + rsaKey.getPrivateKey());
        String rsa = RASUtil.getInstance().encrypt(data, rsaKey.getPublicKey());
        System.out.println("【RSA-公钥加密后:】" + rsa);
        System.out.println("【RSA-私钥解密后:】" + RASUtil.getInstance().decrypt(rsa, rsaKey.getPrivateKey()));
        System.out.println("========================================================");
    }
}
