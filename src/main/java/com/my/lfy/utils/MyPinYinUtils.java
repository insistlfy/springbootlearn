package com.my.lfy.utils;

import cn.hutool.core.util.PinyinUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * MyPinYinUtils
 *
 * @author lfy
 * @date 2020/7/20
 **/
public class MyPinYinUtils extends PinyinUtil {

    public static String getFirstLetter(String chinese) {

        if (StringUtils.isBlank(chinese)) {
            return "";
        }
        return String.valueOf(PinyinUtil.getAllFirstLetter(chinese).toCharArray()[0]);
    }
}
