package com.my.lfy.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * MyStringUtils
 *
 * @author lfy
 * @date 2020/7/28
 **/
public class MyStringUtils {

    public static Pattern PATTERN = Pattern.compile("^[0-9A-Za-z ]$");

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

        System.out.println(test(next));
        System.out.println(test1(next));
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

    /**
     * 如果统计的个数相同，则按照ASCII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。
     * <p>
     * 实现以下接口：
     * 输入一个字符串，对字符中的各个英文字符，数字，空格进行统计（可反复调用）
     * 按照统计个数由多到少输出统计结果，如果统计的个数相同，则按照ASCII码由小到大排序输出
     * 清空目前的统计结果，重新统计
     * 调用者会保证：
     * 输入的字符串以‘\0’结尾。
     * <p>
     * input : (aadddccddc)输入一串字符。
     * output : (dca)对字符中的各个英文字符（大小写分开统计），数字，空格进行统计，并按照统计个数由多到少输出,如果统计的个数相同，则按照ASII码由小到大排序输出 。如果有其他字符，则对这些字符不用进行统计。
     *
     * @param source String
     * @return String
     */

    public static String test(String source) {

        char[] charArr = source.toCharArray();
        Map<String, Integer> resultMap = new HashMap<>();
        for (char c : charArr) {
            if (PATTERN.matcher(String.valueOf(c)).matches()) {
                resultMap.merge(String.valueOf(c), 1, Integer::sum);
            }
        }

        Comparator<Map.Entry<String, Integer>> valueComparator = (o1, o2) -> {
            if (o1.getValue().equals(o2.getValue())) {
                return o1.getKey().charAt(0) - o2.getKey().charAt(0);
            }
            return o2.getValue() - o1.getValue();
        };

        List<Map.Entry<String, Integer>> list = new ArrayList<>(resultMap.entrySet());
        list.sort(valueComparator);

        StringBuilder builder = new StringBuilder();
        list.forEach(e -> builder.append(e.getKey()));

        System.out.println(resultMap);
        System.out.println(list);

        return builder.toString();
    }

    public static String test1(String source) {

        char[] charArr = source.toCharArray();
        Map<String, Integer> resultMap = new HashMap<>();
        for (char c : charArr) {
            if (PATTERN.matcher(String.valueOf(c)).matches()) {
                resultMap.merge(String.valueOf(c), 1, Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> entryList = resultMap.entrySet().stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue().equals(o2.getValue())) {
                        return o1.getKey().charAt(0) - o2.getKey().charAt(0);
                    }
                    return o2.getValue() - o1.getValue();
                })
                .collect(Collectors.toList());

        StringBuilder builder = new StringBuilder();
        entryList.forEach(e -> builder.append(e.getKey()));
        return builder.toString();
    }
}
