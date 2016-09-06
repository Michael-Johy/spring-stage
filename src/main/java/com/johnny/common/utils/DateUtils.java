package com.johnny.common.utils;

import com.johnny.common.constant.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * DateUtils is used to format/parse DateFormat/Date. It's encouraged to use this class to define common
 * date and time formats(string literals eg:"yyyyMMdd") but not define everyWhere, and to define common
 * parse-format methods
 * <strong>
 * All Date and Time Formats are referenced to <a href="http://metric1.org/8601.pdf">ISO 8601</a>, which
 * is the International Standard for the representation of dates and times.
 * </strong>
 * <p>
 * Author: johnny01.yang
 * Date  : 2016-08-02 11:47
 */
public class DateUtils {

    private DateUtils() {
    }

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static final String ISO_BASIC_DATE_PATTERN = "yyyyMMdd";

    private static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    private static final String ISO_BASIC_TIMESTAMP_PATTERN = "yyyyMMdd HHmmss";

    private static final String ISO_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> iso_basic_date_format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_BASIC_DATE_PATTERN, Locale.ENGLISH);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> iso_date_format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_DATE_PATTERN, Locale.ENGLISH);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> iso_basic_timestamp_format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_BASIC_TIMESTAMP_PATTERN, Locale.ENGLISH);
        }
    };
    private static final ThreadLocal<SimpleDateFormat> iso_timestamp_format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_TIMESTAMP_PATTERN, Locale.ENGLISH);
        }
    };

    /**
     * format basic date
     *
     * @param date Date Object to format
     * @return "yyyyMMdd"
     */
    public static String formatBasicDate(Date date) {
        SimpleDateFormat sdf = iso_basic_date_format.get();
        return sdf.format(date);
    }

    /**
     * format date
     *
     * @param date Date Object to format
     * @return "yyyy-MM-dd"
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = iso_date_format.get();
        return sdf.format(date);
    }

    /**
     * format basic dataTime
     *
     * @param date Date Object to format
     * @return "yyyyMMdd HHmmss"
     */
    public static String formatBasicDateTime(Date date) {
        SimpleDateFormat sdf = iso_basic_timestamp_format.get();
        return sdf.format(date);
    }

    /**
     * format dateTime
     *
     * @param date Date Object to format
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = iso_timestamp_format.get();
        return sdf.format(date);
    }

    /**
     * format current basic date
     *
     * @return "yyyyMMdd"
     */
    public static String formatCurrBasicDate() {
        return formatBasicDate(new Date());
    }

    /**
     * format current date
     *
     * @return "yyyy-MM-dd"
     */
    public static String formatCurrDate() {
        return formatDate(new Date());
    }

    /**
     * format current datetime
     *
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatCurrDateTime() {
        return formatDateTime(new Date());
    }

    /**
     * format current basic dateTime
     *
     * @return "yyyyMMdd HHmmss"
     */
    public static String formatCurrBasicDateTime() {
        return formatBasicDateTime(new Date());
    }

    /**
     * yyyyMMddHHmmss
     *
     * @return 20160714120101
     */
    public static String formatCurrDateTimeNoPunctuation() {
        return formatCurrDateTime().replace(CommonConstants.MINUS, CommonConstants.EMPTY).replace(CommonConstants.BLANK, CommonConstants.EMPTY).
                replace(CommonConstants.COLON, CommonConstants.EMPTY);
    }

    /**
     * get year of Date
     *
     * @param date Date Object
     * @return year of Date eg:1985
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * get month of Date
     *
     * @param date Date Object
     * @return month of Date eg:07
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * get month of Date
     *
     * @param date Date Object
     * @return day of Date eg:15
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取前一天日期
     */
    public static Date getLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        Date tmp = calendar.getTime();
        calendar.clear();
        calendar = null;
        return tmp;
    }

    /**
     * 前一天日期转化为 yyyy-MM-dd格式
     */
    public static String getLastDateAsYYYY_MM_DDString() {
        return iso_date_format.get().format(getLastDate());
    }


    /**
     * 前一天日期转化为 yyyyMMdd格式
     *
     * @return formatted
     */
    public static String getLastDateAsYYYYMMDDString() {
        return new SimpleDateFormat("yyyyMMdd").format(getLastDate());
    }


    /**
     * parse String-formatted date to {@linkplain Date with hour:minute:minute set to 00:00:00 }
     *
     * @param dateStr "2015-12-12"
     * @return new Date(2015,12,12)
     */
    public static Date parseDateStr(String dateStr) {
        SimpleDateFormat sdf = iso_date_format.get();
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * parse String-formatted dateTime to {@link Date}
     *
     * @param dateTimeStr format:"2015-12-12 12:55:55"
     * @return new Date(2015,12,12,12,55,55)
     */
    public static Date parseDateTimeStr(String dateTimeStr) {
        SimpleDateFormat sdf = iso_timestamp_format.get();
        Date date = null;
        try {
            date = sdf.parse(dateTimeStr);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return date;
    }

    /**
     * parse String-formatted timeMillis to {@link Date}
     *
     * @param millis "1111111"
     * @return parsed {@link Date} or null when NumberFormatException thrown
     */
    public static Date parseMillisStr(String millis) {
        try {
            Long mills = Long.valueOf(millis);
            return parseMillisLong(mills);
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * parse Long-formatted timeMillis to {@link Date}
     *
     * @param millis 1111111
     * @return parsed {@link Date}
     */
    public static Date parseMillisLong(Long millis) {
        return new Date(millis);
    }
}
