package com.my.lfy.utils;

import cn.hutool.extra.pinyin.PinyinUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * MyPinYinUtils
 *
 * @author lfy
 * @date 2020/7/20
 **/
public final class MyPinYinUtils extends PinyinUtil {

    public static String getFirstLetter(String chinese) {

        if (StringUtils.isBlank(chinese)) {
            return "";
        }
        return PinyinUtil.getFirstLetter(chinese, "-").split("-")[0];
    }
}
