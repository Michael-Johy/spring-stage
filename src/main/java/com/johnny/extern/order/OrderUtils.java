package com.johnny.extern.order;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Description:
 * <p>
 * Author: johnny01.yang
 * Date  : 2017-03-20 17:13
 */
public class OrderUtils {
    /**
     * 获取17位流水号
     */
    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return df.format(date);
    }

    /**
     * 获取14位流水号
     */
    public static String getOrderNum14len() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public static String getOrderNum(String userId) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dtFmt = df.format(date);
        String tempUserId = StringUtils.leftPad(userId, 2, "0");
        return dtFmt + tempUserId;
    }


    public static void main(String[] args) {
        String orderNum = OrderUtils.getOrderNum() + (new Random().nextInt(9000) + 1000);

    }
}
