package com.my.lfy.utils;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PdfTemplateUtils {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream bos = getData();
        OutputStream os = new FileOutputStream("D:/temp/test10.pdf");
        os.write(bos.toByteArray());
        os.flush();
    }


    public static void fill(){
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
            Map<String, String> map = new HashMap<>();
            map.put("name", "111");
            map.put("date", "111");

            fillPdfCellForm(map, form);

            // true代表生成的PDF文件不可编辑
            stamper.setFormFlattening(true);
            stamper.close();
        } catch (IOException | DocumentException e) {
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

}
