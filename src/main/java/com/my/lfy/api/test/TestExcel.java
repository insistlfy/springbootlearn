package com.my.lfy.api.test;

import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @FileName: TestExcel
 * @Description: TODO
 * @Author: lfy
 * @CreateDate: 2023/7/17 15:19
 * @Version: 1.0
 **/
public class TestExcel {

    public static void main(String[] args) throws IOException {

        ExcelReader reader = ExcelUtil.getReader("excel/企业验证清单06.xlsx");
        reader.addHeaderAlias("统一社会信用代码", "uscc");
        reader.addHeaderAlias("企业名称", "entName");
        List<CellData> dataList = reader.readAll(CellData.class);
        System.out.println(JSON.toJSONString(dataList));
//        ExcelWriter writer = ExcelUtil.getWriter("excel/test.xlsx");
        String requestBody = "{\n" +
                "\t\"businessID\": \"WX00070499\",\n" +
                "\t\"entName\":\"${entName}\",\n" +
                "\t\"entCreditID\":\"${uscc}\",\n" +
                "\t\"indName\":\"万春英\",\n" +
                "     \"indCertID\":\"vE2EknWsXew8QckERFhqsKKwrvRpLGP7VrZh0tuzdzY=\",\n" +
                "\t\"productCode\":\"product_eds_gs\",\n" +
                "\t\"appID\":\"xaccb_cloud\",\n" +
                "\t\"timestamp\":\"1688549079706\",\n" +
                "\t\"signature\":\"8f649291ac668b76d26ee2aa418daf81\"\n" +
                "}";
        FileWriter writer = new FileWriter("src/main/resources/csv/test.csv");
        for (CellData cellData : dataList) {
            String entName = cellData.getEntName();
            String uscc = cellData.getUscc();
            String actRequestBody = requestBody.replace("${entName}", entName)
                    .replace("${uscc}", uscc);
            String post = HttpUtil.post("http://106.75.16.202:18880/exchange/entry/customer/datacollection", actRequestBody);
            writer.write(post + "\n");
        }
        writer.close();
    }

    @Builder
    @Data
    private static class CellData {

        @ExcelEntity(name = "统一社会信用代码")
        private String uscc;

        @ExcelEntity(name = "企业名称")
        private String entName;
    }
}
