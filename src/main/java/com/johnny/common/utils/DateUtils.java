package com.johnny.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * This DateUtils is to format {@link Date}
 * <p>
 * Created by johnny01.yang on 2016/7/12.
 */
public class DateUtils {

    private static final Logger logger = Logger.getLogger(DateUtils.class);

    public static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    public static final int ISO_DATE_PATTERN_LENGTH = 10;

    public static final String ISO_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final int ISO_TIMESTAMP_PATTERN_LENGTH = 19;

    public static final String GMT_8 = "GMT+8:00";

    public static ThreadLocal<SimpleDateFormat> iso_date_format = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_DATE_PATTERN);
        }
    };

    public static ThreadLocal<SimpleDateFormat> iso_timestamp_format = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat(ISO_TIMESTAMP_PATTERN);
        }
    };

    /**
     * format date
     *
     * @param date Date Object
     * @return "yyyy-MM-ddd"
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = iso_date_format.get();
        sdf.setTimeZone(TimeZone.getTimeZone(GMT_8));
        return sdf.format(date);
    }

    /**
     * parse date
     *
     * @param dateStr "yyyy-MM-dd"
     * @return parse date with format("yyyy-MM-dd")
     */
    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = iso_date_format.get();
        sdf.setTimeZone(TimeZone.getTimeZone(GMT_8));
        return parse(sdf, dateStr);
    }

    /**
     * format dateTime
     *
     * @param date Date Object
     * @return "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatDateTime(Date date) {
        SimpleDateFormat sdf = iso_timestamp_format.get();
        sdf.setTimeZone(TimeZone.getTimeZone(GMT_8));
        return sdf.format(date);
    }

    /**
     * parse date
     *
     * @param dateTimeStr "yyyy-MM-dd HH:mm:ss"
     * @return dateTime with format("yyyy-MM-dd HH:mm:ss")
     */
    public static Date parseDateTime(String dateTimeStr) {
        SimpleDateFormat sdf = iso_timestamp_format.get();
        sdf.setTimeZone(TimeZone.getTimeZone(GMT_8));
        return parse(sdf, dateTimeStr);
    }

    /**
     * method to parse date-String
     *
     * @param sdf     SimpleDateFormat
     * @param dateStr date-string
     * @return parsed Date
     */
    public static Date parse(SimpleDateFormat sdf, String dateStr) {
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("Parse " + dateStr + " error");
            return null;
        }
        return date;
    }

    /**
     * get date-string millis
     *
     * @param dateStr date-string
     * @return millis
     */
    public static Long toMillis(String dateStr) {
        if (StringUtils.isBlank(dateStr))
            return null;
        Date date;
        if (StringUtils.length(dateStr) == ISO_DATE_PATTERN_LENGTH) {
            date = parseDate(dateStr);
        } else {
            date = parseDateTime(dateStr);
        }
        if (date == null)
            return null;
        return date.getTime();
    }

    /**
     * format new Date() to "yyyy-MM-dd"
     */
    public static String formatCurrDate() {
        return formatDate(new Date());
    }

    /**
     * format new Date() to "yyyy-MM-dd HH:mm:ss"
     */
    public static String formatCurrDateTime() {
        return formatDateTime(new Date());
    }
}
