package com.my.lfy.datasource.druid;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @FileName: DruidUtil
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/12/7 17:50
 * @Version: 1.0
 **/
public class DruidUtil {

    public static void main(String[] args) throws Exception {
        String password = "test_cydb_mysql";
        System.out.println("明文密码: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];
        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        System.out.println("password:" + password);

        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密后:" + decryptPassword);
    }
}
