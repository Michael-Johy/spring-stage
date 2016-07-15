package com.johnny.common.utils;

import org.apache.commons.lang3.StringUtils;
import sun.swing.StringUIClientPropertyKey;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by johnny01.yang on 2016/7/12.
 */
public class NumberUtils {

    /**
     * format the value with scale
     *
     * @param value value to format eg:1.2222
     * @param scale scale  2
     * @return formated value 1.22
     */
    public static Double fmtNumber(Double value, int scale) {
        if (value == null || value.isNaN() || value.isInfinite())
            return null;
        BigDecimal bg = new BigDecimal(value);
        return bg.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str){
        return StringUtils.isNumeric(str);
    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.fmtNumber(1.2222, 2));
    }

}
