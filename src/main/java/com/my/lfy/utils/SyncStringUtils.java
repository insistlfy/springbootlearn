package com.my.lfy.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * SyncStringUtils
 *
 * @author lfy
 * @date 19-4-28
 **/
public class SyncStringUtils extends StringUtils {

    public static Pattern pattern = Pattern.compile("^[A-Za-z]+$");

    /**
     * 对象toString处理
     *
     * @param obj Object
     * @return String
     */
    public static String valueOf(Object obj) {
        return (null == obj) ? "" : obj.toString();
    }

    /**
     * 判断是否是空对象
     *
     * @param obj Object
     * @return boolean
     */
    public static boolean isObjEmpty(Object obj) {
        return (null == obj) || ("" == obj);
    }

    /**
     * base64字符串转byte[]
     *
     * @param base64Str String
     * @return byte
     */
    public static byte[] base64String2ByteFun(String base64Str) {
        return Base64.decodeBase64(base64Str);
    }

    /**
     * byte[]转base64
     *
     * @param b byte
     * @return String
     */
    public static String byte2Base64StringFun(byte[] b) {
        return Base64.encodeBase64String(b);
    }

    /**
     * 判断字符串是否已字母开头
     *
     * @param str String
     * @return boolean
     */
    public static boolean startWithChar(String str) {
        if (SyncStringUtils.isNotBlank(str)) {
            String startChar = str.trim().substring(0, 1);
            return pattern.matcher(startChar).matches();
        }
        return false;
    }


    public static void main(String[] args) {
        String str = "123456";

        System.out.println(str.indexOf("1"));
        System.out.println(str.endsWith("123456"));
        System.out.println(str.substring(1));
    }
}
