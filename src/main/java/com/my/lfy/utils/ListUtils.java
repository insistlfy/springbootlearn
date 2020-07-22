package com.my.lfy.utils;

import cn.hutool.core.util.PinyinUtil;
import com.my.lfy.utils.MyPinYinUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ListUtils
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class ListUtils {

    private static final String[] ALL_LETTER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {

        List<String> source = new ArrayList<>();
        source.add("张三");
        source.add("a");
        source.add("小明");
        source.add("#");
        System.out.println(sorted(source));
        System.out.println("===============================");
        System.out.println(retrieve(source));

        System.out.println(PinyinUtil.getFirstLetter('张'));
        System.out.println(PinyinUtil.getAllFirstLetter("中国"));
        System.out.println(MyPinYinUtils.getFirstLetter("中国"));
        System.out.println(PinyinUtil.getPinYin("中国"));


    }

    /**
     * 模拟微信好友列表实现
     *
     * @param source List
     * @return List
     */
    public static List<String> sorted(List<String> source) {

        List<String> target = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return target;
        }
        return source.stream()
                .sorted(Comparator.comparing(MyPinYinUtils::getFirstLetter))
                .collect(Collectors.toList());
    }

    /**
     * 模拟微信好友列表字母检索
     *
     * @param source List
     */
    public static Map<String, List<String>> retrieve(List<String> source) {
        Map<String, List<String>> target = new LinkedHashMap<>(36);
        for (String letter : ALL_LETTER) {
            target.put(letter, new ArrayList<>());
        }
        if (CollectionUtils.isEmpty(source)) {
            return target;
        }
        source.forEach(e -> {
            String letter = MyPinYinUtils.getFirstLetter(e).toUpperCase();
            List<String> temp = target.get(letter);
            if (null != temp) {
                temp.add(e);
            }
        });

        return target;
    }
}
