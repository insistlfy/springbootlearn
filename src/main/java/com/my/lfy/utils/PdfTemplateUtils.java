package com.my.lfy.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class PdfTemplateUtils {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bos = getData();
        OutputStream os = new FileOutputStream("D:/tmp/test10.pdf");
        os.write(bos.toByteArray());
        os.flush();
    }


    public static void fill() {
        // 模板文件路径
        String inputFileName = "D:/temp/test(1).pdf";
        // 生成的文件路径
        String outputFileName = "D:/temp/test9.pdf";

        OutputStream os = null;
        PdfStamper ps = null;
        PdfReader reader = null;

        try {
            os = new FileOutputStream(new File(outputFileName));
            // 2 读入pdf表单
            reader = new PdfReader(inputFileName);
            // 3 根据表单生成一个新的pdf
            ps = new PdfStamper(reader, os);
            // 4 获取pdf表单
            AcroFields form = ps.getAcroFields();
            // 6查询数据================================================
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("name", "李四");
            data.put("date", "2022-11-24");
            // 7遍历data 给pdf表单表格赋值
            for (String key : data.keySet()) {
                form.setField(key, data.get(key).toString());
            }
            ps.setFormFlattening(true);
            ps.flush();
            System.out.println("===============PDF导出成功=============");
        } catch (Exception e) {
            System.out.println("===============PDF导出失败=============");
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                reader.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ByteArrayOutputStream getData() {
        ClassPathResource classPathResource = new ClassPathResource("template/test(1).pdf");
        PdfReader reader;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            reader = new PdfReader(classPathResource.getInputStream());
            bos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
//            Map<String, String> map = new HashMap<>();
//            map.put("name", "张三");
//            map.put("date", "2022-11-24");
//
//            fillPdfCellForm(map, form);

            FillData fillData = FillData.builder()
                    .name("张三")
                    .date("2022-11-24")
                    .build();
            fill(fillData,form);

            // true代表生成的PDF文件不可编辑
            stamper.setFormFlattening(true);
            stamper.close();
        } catch (IOException | DocumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return bos;
    }

    private static void fillPdfCellForm(Map<String, String> map, AcroFields form) throws IOException, DocumentException {
        for (Map.Entry entry : map.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            form.setField(key, value);
        }
    }

    private static <T> void fill(T fillData, AcroFields form) throws IllegalAccessException, DocumentException, IOException {
        Field[] declaredFields = fillData.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            form.setField(declaredField.getName(), String.valueOf(declaredField.get(fillData)));
        }
    }

    @Data
    @Builder
    public static class FillData implements Serializable {

        private static final long serialVersionUID = -8580302177661211873L;
        private String name;

        private String date;

        @Tolerate
        public FillData() {
        }
    }
}
