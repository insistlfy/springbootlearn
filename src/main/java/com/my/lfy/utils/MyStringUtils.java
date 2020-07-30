package com.my.lfy.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * MyStringUtils
 *
 * @author lfy
 * @date 2020/7/28
 **/
public class MyStringUtils {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        System.out.println(reverse(next));

        String next1 = scanner.nextLine();
        System.out.println(reverse(next1));
        System.out.println("===============================================");

        System.out.println(reverse("m jbrwbmamzlz bkjokxancguvcoc"));
        System.out.println(new StringBuilder().append("1  23").reverse());
        System.out.println("===============================================");

        String str = "1234";
        System.out.println(str.substring(0, str.length() / 2));
        System.out.println(str.substring(str.length() / 2));
        System.out.println("===============================================");

        String str1 = "12345";
        System.out.println(str1.indexOf(str1.length() / 2));
        System.out.println(str1.substring(0, str1.length() / 2));
        System.out.println(str1.substring(str1.length() / 2));
        System.out.println("===============================================");
    }

    /**
     * 字符串反转
     *
     * @param source String
     * @return String
     */
    public static String reverse(String source) {

        char[] charArr = source.toCharArray();
        StringBuilder target = new StringBuilder();

        for (int i = 0; i < charArr.length; i++) {
            target.append(charArr[charArr.length - 1 - i]);
        }
        return target.toString();
    }

    /**
     * 字符串反转
     *
     * @param source String
     * @return String
     */
    public static String reverseNew(String source) {

        if (StringUtils.isBlank(source) || source.length() == 1) {
            return source;
        }

        if (source.length() % 2 == 0) {

            String before = source.substring(source.length() / 2 - 1);
            String after = source.substring(source.length() / 2, source.length() - 1);
            return "";

        }


        return "";
    }
}
