package com.my.lfy.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * ExcelUtils
 *
 * @author lfy
 * @date 2020/9/8
 **/
@Slf4j
public class ExcelUtils {

    /**
     * 数据导入导出相关
     */
    private static final String DATA_IMPORT_PREFIX = "【数据导入相关】";
    private static final String DATA_EXPORT_PREFIX = "【数据导出相关】";
    public final static String CHARSET_UTF8 = "UTF-8";

    /**
     * 导入
     *
     * @param excelFile MultipartFile
     * @param clazz     Class
     * @param <T>       T
     * @return ExcelImportResult
     * @throws Exception e
     */
    public static <T> ExcelImportResult<T> importExcel(MultipartFile excelFile, Class<T> clazz) throws Exception {

        ImportParams importParams = new ImportParams();
        //头占一行
//        importParams.setHeadRows(1);
        //标题占一行
//        importParams.setTitleRows(1);
        //开启验证
//        importParams.setNeedVerfiy(true);
        //自定义验证器 TODO
//        importParams.setVerifyHandler();

        ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(excelFile.getInputStream(), clazz, importParams);
        Optional.ofNullable(result).ifPresent(e -> {
            log.info("{}-import successful data size {}.", DATA_IMPORT_PREFIX, e.getList().size());
            log.info("{}-import failed data size {}.", DATA_IMPORT_PREFIX, e.getFailList().size());
        });
        return result;
    }


    /**
     * 导出
     *
     * @param fileName     文件名
     * @param exportParams 导出参数
     * @param response     HttpServletResponse
     * @param clazz        导出Excel 模板类
     * @param list         导出的数据集合
     * @param <T>          T
     */
    public static <T> void export(String fileName, ExportParams exportParams, HttpServletResponse response,
                                  Class<T> clazz, List<T> list) {
        log.info("{}-start export {}.", DATA_EXPORT_PREFIX, fileName);
        Optional.of(exportParams).ifPresent(e -> e.setStyle(ExcelStyleUtil.class));
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, clazz, list);
        Optional.ofNullable(workbook).ifPresent(s -> downloadExcel(fileName, response, s));
    }

    /**
     * 下载
     *
     * @param fileName 文件名
     * @param response HttpServletResponse
     * @param workbook Workbook
     */
    public static void downloadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            log.info("{}-start download {}.", DATA_EXPORT_PREFIX, fileName);
            response.setCharacterEncoding(CHARSET_UTF8);
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", CHARSET_UTF8));
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            log.error("{}-download failed.", DATA_EXPORT_PREFIX, e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error("{}-workbook close failed.", DATA_EXPORT_PREFIX, e);
                }
            }
        }
    }
}
