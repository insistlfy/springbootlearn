//package com.my.lfy.api.test;
//
//import com.alibaba.excel.EasyExcel;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.enums.WriteDirectionEnum;
//import com.alibaba.excel.write.metadata.WriteSheet;
//import com.alibaba.excel.write.metadata.fill.FillConfig;
//import com.alibaba.excel.write.metadata.fill.FillWrapper;
//import com.my.lfy.exception.ServiceException;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.util.*;
//
///**
// * @FileName: TestEasyExcel
// * @Description: esayExcel测试
// * @Author: Lify
// * @CreateDate: 2022/11/7 15:13
// **/
//@Slf4j
//public class TestEasyExcel {
//
//    public static void main(String[] args) {
//
//
//    }
//
//
//    @Test
//    public void compositeFill() {
//        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
//        // {} 代表普通变量 {.} 代表是list的变量 {前缀.} 前缀可以区分不同的list
//        String templateFileName = "";
//        String fileName = "";
//
//        // 方案1
//        try {
//            ExcelWriter excelWriter = EasyExcel.write(fileName).withTemplate(templateFileName).build();
//            WriteSheet writeSheet = EasyExcel.writerSheet().build();
//            FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();
//            // 如果有多个list 模板上必须有{前缀.} 这里的前缀就是 data1，然后多个list必须用 FillWrapper包裹
//            excelWriter.fill(new FillWrapper("data1", data()), fillConfig, writeSheet);
//            excelWriter.fill(new FillWrapper("data1", data()), fillConfig, writeSheet);
//            excelWriter.fill(new FillWrapper("data2", data()), writeSheet);
//            excelWriter.fill(new FillWrapper("data2", data()), writeSheet);
//            excelWriter.fill(new FillWrapper("data3", data()), writeSheet);
//            excelWriter.fill(new FillWrapper("data3", data()), writeSheet);
//
//            Map<String, Object> map = new HashMap<>();
//            //map.put("date", "2019年10月9日13:28:28");
//            map.put("date", new Date());
//            excelWriter.fill(map, writeSheet);
//        } catch (Exception e) {
//            log.error("导出失败，请稍后重试", e);
//            throw new ServiceException("导出失败，请稍后重试");
//        }
//
//    }
//
//    private List<String> data() {
//        return new ArrayList<>();
//    }
//}
