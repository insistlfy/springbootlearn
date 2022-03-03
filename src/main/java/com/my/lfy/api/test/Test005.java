package com.my.lfy.api.test;

public class Test005 {

    public static void main(String[] args) {

//        String key = "ce0d9ba608d44785827279bc89bd1397";
//        String SecretKey = "5B49298C131CAECFFC04C0D2EA4933B8";
//        String timeMillis = String.valueOf(System.currentTimeMillis() / 1000);
//        String token = DigestUtils.md5Hex(key + timeMillis + SecretKey).toUpperCase();
//        System.out.println(timeMillis);
//        System.out.println(token);
//        System.out.println(token.length());

        int count = 10;
        int mod = 7 % 2;
        if (0 == mod) {
            count--;
        }
        System.out.println(count);
    }
}
