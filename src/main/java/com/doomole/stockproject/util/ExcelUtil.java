package com.doomole.stockproject.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtil {
    public static String getValue(Cell cell) {
        String value = "";
        if(cell == null) {
            return value;
        }
        switch(cell.getCellType()) {
            case FORMULA:
                value = cell.getNumericCellValue() + "";
                break;
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                } else {
                    // 지수변환 방지
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    value = bd.toString();
                    if(value.indexOf('.') > 0){
                        value = bd.setScale(4, BigDecimal.ROUND_HALF_UP) + "";
                    }
                }
                break;
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case BLANK:
                value = "";
                break;
            case ERROR:
                value = cell.getErrorCellValue() + "";
                break;
            default:
                value = cell.getStringCellValue();
        }

        return value;
    }
}
