package com.my.lfy.utils;

import com.alibaba.fastjson.JSON;
import com.my.lfy.exception.ServiceException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * MyStringUtils
 * <p>
 * StringJoiner
 * 参考链接 ： https://mp.weixin.qq.com/s?__biz=MzUzMTA2NTU2Ng==&mid=2247508922&idx=2&sn=f3dfc85a51d15dff5a3abd3712a9a3e8
 * &chksm=fa4adc0bcd3d551d8397aa8e028e8a506b3640b224de44502a0e9a480517d35030b44943d468&mpshare=1&scene=1&srcid
 * =0222eKWNtd0EfRXpga1ydoiW&sharer_sharetime=1613977110783&sharer_shareid=86689f05efe7bba4ecd64bd36ea9cc52#rd
 *
 * @author lfy
 * @date 2020/7/28
 **/
public class MyStringUtils {

    public static Pattern PATTERN = Pattern.compile("^[0-9A-Za-z ]$");

    public static void main(String[] args) {

//        Scanner scanner = new Scanner(System.in);
//        String next = scanner.nextLine();
//
//        System.out.println(reverse(next));
//        System.out.println(reverseNew(next));
//        System.out.println("=====================================");
//
//
//        System.out.println(findCharTimes(next, 's'));
//        System.out.println(findCharTimesNew(next, 's'));
//        System.out.println("=====================================");
//
//        System.out.println(strSort(next));
//        System.out.println(strSort1(next));
//        System.out.println("=====================================");

        MyStringUtils myStringUtils = new MyStringUtils();
//        String str = new String("good");
        String str = "good";
        char[] ch = {'a', 'b', 'c'};
        int a = 1;
        myStringUtils.change(str, ch, a);
        System.out.println(str);
        System.out.println(ch);
        System.out.println(a);
        System.out.println("=============change========================");

        StringJoiner joiner = new StringJoiner(",");
        System.out.println(joiner.toString());
        joiner.add("1").add("2").add("3");
        System.out.println(joiner.toString());
        System.out.println("==============joiner=======================");

        // test
        System.out.println(Math.round(-1.5));
        StringBuilder builder = new StringBuilder();
        builder.append("123456");
        System.out.println(builder.reverse().toString());
        System.out.println(reverse("123456"));
        System.out.println(reverseNew("123456"));
        System.out.println("================builder.reverse()=====================");

        String[] strArr = {"Hello", "World"};
        List<String> strList = Arrays.asList(strArr);
        System.out.println(deRepeat(strList));
        System.out.println(deRepeat(strList).indexOf("H"));
        System.out.println("================deRepeat=====================");

        System.out.println(JSON.toJSONString("hello".split("")));
        System.out.println(deRepeatNew(strList));
        System.out.println("================deRepeatNew=====================");
    }

    public void change(String str1, char ch1[], int b) {
        b = 100;
        // 等价与 str1  = new String("test ok");
        str1 = "test ok";
        ch1[0] = 'g';
        System.out.println(str1);
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

        Optional.ofNullable(source).orElseThrow(() -> new ServiceException("参数不能为空"));
        char[] charArr = source.toCharArray();

        Stack<Character> stack = new Stack<>();
        for (char c : charArr) {
            stack.push(c);
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charArr.length; i++) {
            builder.append(stack.pop());
        }
        return builder.toString();
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
    public static String strSort(String source) {

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

    public static String strSort1(String source) {

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

    /**
     * 统计一个字符串中某个字符出现的次数
     *
     * @param source     String
     * @param targetChar char
     * @return int
     */
    public static int findCharTimes(String source, char targetChar) {
        Optional.ofNullable(source).orElseThrow(() -> new ServiceException("参数不能为空"));

        char[] charArr = source.toCharArray();
        int count = 0;
        for (char c : charArr) {
            if (c == targetChar) {
                count++;
            }
        }
        return count;
    }

    /**
     * 统计一个字符串中某个字符出现的次数
     *
     * @param source     String
     * @param targetChar char
     * @return int
     */
    public static int findCharTimesNew(String source, char targetChar) {
        Optional.ofNullable(source).orElseThrow(() -> new ServiceException("参数不能为空"));

        int originLength = source.length();
        source = source.replace(String.valueOf(targetChar), "");
        int newLength = source.length();
        return originLength - newLength;
    }

    /**
     * 字符串去重
     *
     * @param source String
     * @return String
     */
    public static String deRepeat(@NotBlank String source) {
        StringBuilder builder = new StringBuilder();
        for (String str : source.split("")) {
            if (builder.indexOf(str) < 0) {
                builder.append(str);
            }
        }
        return builder.toString();
    }

    /**
     * 对数组中的字符串去重
     * 假如我们有这样一个需求给定单词列表["Hello","World"]，你想要返回列表HeloWrd
     *
     * @param sourceList String[]
     * @return String
     */
    public static String deRepeat(@NotNull List<String> sourceList) {
        StringBuilder builder = new StringBuilder();
        sourceList.forEach(e -> {
            for (char c : e.toCharArray()) {
                builder.append(c);
            }
        });
        return deRepeat(builder.toString());
    }

    /**
     * 对数组中的字符串去重
     * 假如我们有这样一个需求给定单词列表["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]
     *
     * @param sourceList List<String>
     * @return List<String>
     */
    public static List<String> deRepeatNew(@NotNull List<String> sourceList) {
        return sourceList.stream()
                .map(e -> e.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
