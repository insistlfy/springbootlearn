package com.my.lfy.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CsvUtils
 *
 * @author lfy
 * @date 2020/9/10
 **/
@Slf4j
public class CsvUtils {
    /**
     * 获取CSV
     *
     * @param path String
     * @return List list
     */
    public static List<String> getCsv(String path) {

        List<String> list = new ArrayList<>();
        InputStream inputStream = CsvUtils.class.getResourceAsStream(path);
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        String everyLine = "";

        try {
            if (null == br) {
                log.warn("未获取到csv文件...");
                return list;
            }

            while ((line = br.readLine()) != null) {
                everyLine = line;
                list.add(everyLine);
            }
            log.info("list-count = {}", list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
