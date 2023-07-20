package com.my.lfy.api.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @FileName: TestFastJson
 * @Description: fastjson 测试
 * @Author: lfy
 * @CreateDate: 2023/7/20 9:36
 * @Version: 1.0
 **/
public class TestFastJson {
    public static void main(String[] args) {

        JSONArray jsonArray = new JSONArray();
        Map<String, Object> map1 = new HashMap<>(4);
        map1.put("code", "200");
        map1.put("msg", "success");
        map1.put("data", "1");
        Map<String, Object> map2 = new HashMap<>(4);
        map2.put("code", "200");
        map2.put("msg", "success");
        map2.put("data", "2");
        Map<String, Object> map3 = new HashMap<>(4);
        map3.put("code", "200");
        map3.put("msg", "success");
        map3.put("data", "3");
        jsonArray.add(map1);
        jsonArray.add(map2);
        jsonArray.add(map3);
        List<TestJson> testJsonList = JSONArray.parseArray(jsonArray.toString(), JSONObject.class).stream()
                .map(e -> TestJson.builder().data(e.getString("data")).build()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(testJsonList));

//        JSONArray parseArray = JSONArray.parseArray(JSON.toJSONString(testJsonList));

    }

    @Data
    @Builder
    public static class TestJson implements Serializable {

        private static final long serialVersionUID = -4687849937660224568L;

        private String data;
    }
}
