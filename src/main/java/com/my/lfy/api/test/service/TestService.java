package com.my.lfy.api.test.service;

import com.alibaba.fastjson.JSON;
import com.my.lfy.api.retry.service.RetryService;
import com.my.lfy.api.springtask.SpringTaskConfig;
import com.my.lfy.api.test.model.DicModel;
import com.my.lfy.api.test.model.TestModel;
import com.my.lfy.api.transaction.mapper.CommonMapper;
import com.my.lfy.utils.CsvUtils;
import com.my.lfy.utils.ExcelUtils;
import com.my.lfy.utils.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * TestService
 *
 * @author lfy
 * @date 2020/7/29
 **/
@Slf4j
@Service
public class TestService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private RetryService retryService;

    @Autowired
    private SpringTaskConfig springTaskConfig;

    public List<String> test02() {
        List<String> params = new ArrayList<>();
        params.add("REG_REFUSE_REASON");
        return commonMapper.queryList(params);
    }

    public boolean test04() {

        log.info("test-retry...");
        springTaskConfig.executor().execute(() -> retryService.test("sad"));

        springTaskConfig.executor().execute(() -> retryService.test01("sad"));

        log.info("test01---end");

        test02();
        return true;
    }

    public void testExcel(HttpServletRequest request, HttpServletResponse response, String filePath,
                          Integer sheetAt, Integer cellAt, String source, String target) {
        try {
            Workbook workbook = WorkbookFactory.create(new File(filePath));
            Sheet sheet = workbook.getSheetAt(sheetAt);
            int totalRow = sheet.getLastRowNum();
            for (int i = 0; i <= totalRow; i++) {
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                Cell cell = row.getCell(cellAt);
                if (cell != null && cell.getStringCellValue().equals(source)) {
                    cell.setCellValue(target);
                }
            }
            ExcelUtils.downloadExcel("test", response, workbook);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public Set<String> testCsv() {

        Set<String> result = new HashSet<>();
        List<String> cardNoList = CsvUtils.getCsv("/csv/cardNo.csv");
        log.info("cardNoList size is {}.", cardNoList.size());

        List<List<String>> tempList = ListUtils.partition(cardNoList, 1000);
        log.info("tempList size is {}.", tempList.size());
        for (List<String> list : tempList) {
            List<String> tempMap = commonMapper.queryPatientInfo(list);
            result.addAll(tempMap);
        }
        log.info("result size is {}.", result.size());
        return result;
    }

    public String testSign(User user) {


        return null;
    }

    public List<DicModel> testMybatis(Integer limit) {

        Cursor<DicModel> cursor = commonMapper.scan(limit);
        cursor.forEach(e -> e.setKeyword("123"));
        return null;
    }

    public void testPostJson(TestModel testModel, Integer page) {
        log.info("Retrofit testPostJson,testModel={},page={}.", JSON.toJSONString(testModel), page);
    }

    public void testGet(Long id, String name) {
        log.info("Retrofit testGet,id={},name ={}.", id, name);
    }

    public void testPut(Long id) {
        log.info("Retrofit testPut,id={}.", id);
    }
}
