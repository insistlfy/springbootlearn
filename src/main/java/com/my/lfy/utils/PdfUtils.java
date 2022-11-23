package com.my.lfy.utils;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfUtils {

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("name", "杨杰");
        map.put("date", "2022-11-23");

//        String path = PdfUtils.class.getResource("/template").getPath();
//        System.out.println("path:"+path);
//        String sourceFile = path + File.separator + "test.pdf";
        String sourceFile = "D:/temp/test(1).pdf";
        String targetFile = "D:/temp/test9.pdf";
        genPdf(map, sourceFile, targetFile);

//        System.out.println("获取pdf表单中的fieldNames:"+getTemplateFileFieldNames(sourceFile));
//        System.out.println("读取文件数组:"+fileBuff(sourceFile));
//        System.out.println("pdf转图片:"+pdf2Img(new File(targetFile),imageFilePath));
    }

    private static void genPdf(Map<String, String> map, String sourceFile, String targetFile) throws IOException {
        File templateFile = new File(sourceFile);
        fillParam(map, FileUtils.readFileToByteArray(templateFile), targetFile);
    }

    /**
     * Description: 使用map中的参数填充pdf，map中的key和pdf表单中的field对应 <br>
     *
     * @return
     * @author mk
     * @Date 2018-11-2 15:21 <br>
     * @Param
     */
    public static void fillParam(Map<String, String> fieldValueMap, byte[] file, String contractFileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(contractFileName);
            PdfReader reader = null;
            PdfStamper stamper = null;
            BaseFont base = null;
            try {
                reader = new PdfReader(file);
                stamper = new PdfStamper(reader, fos);
                stamper.setFormFlattening(true);
//                base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                AcroFields acroFields = stamper.getAcroFields();
//                for (String key : acroFields.getFields().keySet()) {
//                    acroFields.setFieldProperty(key, "textfont", base, null);
//                    acroFields.setFieldProperty(key, "textsize", new Float(9), null);
//                }
                if (fieldValueMap != null) {
                    for (String fieldName : fieldValueMap.keySet()) {
                        acroFields.setField(fieldName, fieldValueMap.get(fieldName));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (stamper != null) {
                    try {
                        stamper.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    reader.close();
                }
            }

        } catch (Exception e) {
            System.out.println("填充参数异常");
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fos);
        }
    }
}
