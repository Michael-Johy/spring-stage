package com.johnny.common.utils.excel;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This excel util can convert certain column to Object field
 *
 * Created by johnny01.yang on 2016/6/20.
 */
public class ExcelObjectResolveUtils {

    private static final Logger log = LoggerFactory.getLogger(ExcelObjectResolveUtils.class);

    private static final int MAX_ROWS = 1001;

    private final File excelFile;
    private Class clazz;
    private XSSFWorkbook wb;

    private String titleValidMsg;
    private boolean titleValid;

    /**
     * 列索引 -> 列名
     */
    private static Map<Integer, String> index2ColNameMap = new HashMap<>(16);

    /**
     * 列名 -> clazz字段名
     */
    private static Map<String, Field> colName2FiledMap = new HashMap<>(16);

    public static ExcelObjectResolveUtils mapExcelToModel(File excelFile, Class clazz) {
        return new ExcelObjectResolveUtils(excelFile, clazz);
    }

    private ExcelObjectResolveUtils(File excelFile, Class clazz) {
        this.excelFile = excelFile;
        this.clazz = clazz;
        this.titleValid = true;
        this.titleValidMsg = "";
        validTitle();
    }

    /**
     * valid title
     */
    private void validTitle() {
        FileInputStream is = null;
        try {
            is = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(is);
        } catch (Exception e) {
            log.error("Excel文件不存在");
            titleValid = false;
            titleValidMsg = "Excel文件不存在\n";
            return;
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("IOException", e);
                    titleValid = false;
                    titleValidMsg = titleValidMsg.concat("文件关闭失败\n");
                }
            }
        }

        Set<String> aliasCols = Sets.newHashSet();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String aliasName = field.isAnnotationPresent(NameAlias.class) ? field.getAnnotation(NameAlias.class).value() : field.getName();
                colName2FiledMap.put(aliasName, field);
                aliasCols.add(aliasName);
            }
        }

        if (wb != null && wb.getNumberOfSheets() > 0) {
            for (int index = 0; index < wb.getNumberOfSheets(); index++) {
                XSSFSheet sheet = wb.getSheetAt(index);
                Set<String> excelCols = Sets.newHashSet();
                if (sheet.getLastRowNum() > 0) {
                    if (sheet.getLastRowNum() > MAX_ROWS) {
                        titleValidMsg = titleValidMsg.concat("记录不能超过1000条").concat(", 出错Sheet: " + index);
                        titleValid = false;
                        continue;
                    }
                    XSSFRow row = sheet.getRow(0);
                    for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
                        XSSFCell cell = row.getCell(cellIndex);
                        String strValue = getStringCellValue(cell);
                        if (!aliasCols.contains(strValue)) {
                            titleValidMsg = titleValidMsg.concat("Excel提供的列名不存在于Model中").concat(", 出错Sheet: " + index).concat(", 出错列名:" + strValue + "\n");
                            titleValid = false;
                            continue;
                        }
                        excelCols.add(strValue);
                        index2ColNameMap.put(cellIndex, strValue);
                    }
                } else if (index == 0) { //当前页是第一页时
                    titleValid = false;
                    titleValidMsg = titleValidMsg.concat("Excel内容少于1行");
                }
                if (aliasCols.size() != excelCols.size() && index == 0) {
                    Set<String> copyAlias = Sets.newHashSet();
                    copyAlias.addAll(aliasCols);
                    titleValidMsg = titleValidMsg.concat("Excel未提供的列名：" + Joiner.on(",").skipNulls().join(CollectionUtils.subtract(aliasCols, excelCols)));
                    titleValid = false;
                }
            }
        } else {
            titleValid = false;
            titleValidMsg = titleValidMsg.concat("Excel文件为空");
        }
    }


    @SuppressWarnings("unchecked")
    public List mapper() throws Exception {
        if (!isTitleValid())
            return null;
        List result = Lists.newArrayList();
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet != null && sheet.getLastRowNum() > 1) {
                for (int rowIndex = 1; rowIndex < sheet.getLastRowNum(); rowIndex++) {
                    XSSFRow row = sheet.getRow(rowIndex);
                    result.add(processRowContent(row));
                }
            }
        }
        return result;
    }

    private Object processRowContent(XSSFRow row) throws Exception {
        if (null == row)
            return null;
        Object instance = clazz.newInstance();
        for (int cellIndex = 0; cellIndex < row.getLastCellNum(); cellIndex++) {
            String strValue = getStringCellValue(row.getCell(cellIndex));
            Field field = colName2FiledMap.get(index2ColNameMap.get(cellIndex));
            if (field != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(instance, strValue);
            }
        }
        return instance;
    }

    public boolean isTitleValid() {
        return titleValid;
    }

    public String getTitleValidMsg() {
        return titleValidMsg;
    }

    private String getStringCellValue(XSSFCell cell) {
        if (null == cell) {
            return "";
        }
        String strCell;
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                try {
                    strCell = String.valueOf(cell.getNumericCellValue());
                } catch (Exception e) {
                    log.error("Excel格式错误");
                    strCell = "";
                }
                break;
            default:
                // CELL_TYPE_BLANK || CELL_TYPE_ERROR || CELL_TYPE_FORMULA 暂时不考虑
                strCell = "";
        }
        return StringUtils.trim(strCell);
    }
}
