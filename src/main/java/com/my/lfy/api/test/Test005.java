package com.my.lfy.api.test;

import com.my.lfy.utils.AESUtil;
import com.my.lfy.utils.DESUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Administrator
 */
@Slf4j
public class Test005 {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("========================================================");
        String data = "LFY";
        System.out.println("加密前:" + data);
        System.out.println("EncryptUtils-MD5:" + DigestUtils.md5DigestAsHex(data.getBytes()));
        System.out.println("========================================================");
        String des = DESUtil.getInstance().encrypt(data);
        System.out.println("DES-加密后:" + des);
        System.out.println("DES-解密后:" + DESUtil.getInstance().decrypt(des));
        System.out.println("========================================================");
        String aes = AESUtil.getInstance().encrypt(data);
        System.out.println("AES-加密后:" + aes);
        System.out.println("AES-解密后:" + AESUtil.getInstance().decrypt(aes));
        System.out.println("========================================================");
    }
}
