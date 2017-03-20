package com.johnny.extern.retry;

import java.util.Calendar;
import java.util.Date;

/**
 * Description: 接口重试时间计算工具类
 * <p>
 * Author: johnny01.yang
 * Date  : 2017-01-10 17:27
 */
public final class RetryUtils {

    private RetryUtils() {
    }

    /**
     * 2小时重试7小时获取重试时间
     *
     * @param retryNo 重试次数
     * @return
     */
    public static Date getRetryTime2H(int retryNo) {
        int minutes = 1000;
        switch (retryNo) {
            case 1:
                minutes = 2;
                break;
            case 2:
                minutes = 8;
                break;
            case 3:
                minutes = 10;
                break;
            case 4:
                minutes = 20;
                break;
            case 5:
                minutes = 20;
                break;
            case 6:
                minutes = 30;
                break;
            case 7:
                minutes = 30;
                break;
            default:
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}
