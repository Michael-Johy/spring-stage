package com.csf.common;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by johnny01.yang on 2016/6/20.
 */
public class ExcelTest {

    @Test
    public void test1(){
        String filePath = "D:\\temp\\vfc-admin.vip.vip.com\\风险名单导入模版_IP.xlsx";
        XSSFWorkbook wb = null;
        try {
            File file = new File(filePath);
            FileInputStream is = new FileInputStream(file);
            wb = new XSSFWorkbook(is);
        }catch (Exception e){
            System.out.println("error");
        }
        if (null != wb){
            XSSFRow row = wb.getSheetAt(0).getRow(0);
            System.out.println("first Num:" + row.getFirstCellNum());
            System.out.println("first NUm:" + row.getCell(row.getFirstCellNum()).getStringCellValue());
            System.out.println("last Num : " + row.getLastCellNum());
        }
    }

    public class Model{
        private String name;
        private Integer age;
        private String addr;
    }

}
