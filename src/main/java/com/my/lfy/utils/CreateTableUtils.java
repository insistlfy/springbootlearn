package com.my.lfy.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.WorkbookUtil;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName: CreateTableUtils
 * @Description: TODO
 * @Author: Lify
 * @CreateDate: 2022/10/11 10:07
 **/
public class CreateTableUtils {

    /**
     * 生成sql文件
     */
    @Test
    public void generateSql() throws IOException {
        System.out.println("开始生成SQL=========================");
        //Map<String, Object> map = readExcel(null,null,"用户表"); //读取单个指定sheet数据
        List<Map<String, Object>> result = readExcel();
        System.out.println("==============共" + result.size() + "张表===============");
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> map : result) {
            List<ExcelEntity> list = (List<ExcelEntity>) map.get("list");
            int size = list.size();
            //表名
            String tableName = (String) map.get("tableName");
            //注释
            String tableNotes = (String) map.get("tableNotes");
            sb.append(String.format("-- %s：%s\n", tableName, tableNotes));
            sb.append(String.format("DROP TABLE IF EXISTS %s;\n", tableName));
            sb.append(String.format("CREATE TABLE %s (\n", tableName));
            for (int i = 0; i < size; i++) {
                ExcelEntity entity = list.get(i);
                sb.append(String.format("\t%s %s", entity.getFiledName(), entity.getFiledType()));
                String type = entity.getFiledType();
                String attribute = entity.getAttribute();
                //设置主键
                if (attribute.contains("主键")) {
                    sb.append(" PRIMARY KEY");
                } else {
                    //字符串、文本设置编码
                    //主键类型为字符时，不设置编码
//                    if (type.contains("char") || type.contains("text")) {
//                        sb.append(" CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
//                    }
                }
                if (attribute.contains("Y")) {
                    sb.append(" NOT NULL COMMENT ");
                } else {
                    sb.append(" NULL DEFAULT NULL COMMENT ");
                }
                sb.append(String.format("'%s'", entity.getNotes()));
                if (i < size - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
//            sb.append(String.format(") ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '%s' ROW_FORMAT = Dynamic;", tableNotes));
            sb.append(String.format(")  COMMENT = '%s' ;", tableNotes));
            sb.append("\n\n");
        }
        //sql输出的位置
        File file = new File("D:\\tmp\\sql\\sql.sql");
        FileOutputStream fos1 = new FileOutputStream(file);
        OutputStreamWriter dos1 = new OutputStreamWriter(fos1);
        dos1.write(sb.toString());
        dos1.close();
        System.out.println("SQL生成完成=========================");
    }


    /**
     * 读取指定sheet的数据，获取表名，字段名、字段类型、字段注释、属性
     */
    public Map<String, Object> readExcel(File file, Workbook book, String sheetName) {
        Map<String, Object> map = new HashMap<>();
        List<ExcelEntity> list = new ArrayList<>();
        if (file == null) {
            file = new File("C:\\Users\\Admin\\Desktop\\1.xlsx");
        }
        if (book == null) {
            //WorkbookUtil hutool的工具类
            book = WorkbookUtil.createBook(file);
        }
        Sheet sheet = book.getSheet(sheetName);
        //第一行是表头，默认是 注释:表名
        Row titleRow = sheet.getRow(0);
        String title = titleRow.getCell(0).toString();
        map.put("tableName", title.split(":")[1]);
        map.put("tableNotes", title.split(":")[0]);
        //第二行是标题，不读取。第三行开始才是数据
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 2; i <= lastRowNum; i++) {
            list.add(getEntity(sheet.getRow(i)));
        }
        map.put("list", list);
        return map;
    }

    /**
     * 读取多个sheet的数据
     */
    public List<Map<String, Object>> readExcel() {
        List<Map<String, Object>> result = new ArrayList<>();
        File file = new File("D:\\Work\\3.0-长银担保\\合作方管理\\合作方管理表设计_20221009 - 副本.xlsx");
        Workbook book = WorkbookUtil.createBook(file);
        int sheetNum = book.getNumberOfSheets();
        for (int i = 0; i < sheetNum; i++) {
            String sheetName = book.getSheetName(i);
            result.add(readExcel(file, book, sheetName));
        }
        return result;
    }

    private ExcelEntity getEntity(Row row) {
        ExcelEntity entity = new ExcelEntity();
        //列数：4列，分别是字段名、字段类型、字段注释、字段属性
        for (int j = 0; j < 4; j++) {
            String str = row.getCell(j).toString();
            if (StrUtil.isNotBlank(str)) {
                switch (j) {
                    case 0:
                        entity.setFiledName(str);
                        break;
                    case 1:
                        entity.setNotes(str);
                        break;
                    case 2:
                        entity.setFiledType(str);
                        break;
                    case 3:
                        entity.setAttribute(str);
                        break;
                }
            }
        }
        return entity;
    }


    @Data
    public static class ExcelEntity {
        /**
         * 字段名称
         */
        private String filedName;
        /**
         * 字段类型
         */
        private String filedType;
        /**
         * 字段注释
         */
        private String notes;
        /**
         * 字段属性
         */
        private String attribute;
    }
}
