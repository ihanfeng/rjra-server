package com.hdg.rjra.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * @title: DateUtils.java
 * @description: 类功能描述
 * @date: 2014年4月24日 上午2:51:23
 * @author: rock
 * @modify by: rock
 * @modify date: 2014年4月24日 上午2:51:23
 * @modify content: 修改内容
 * 
 */
public class DateUtils {

    /**
     * 
     * @param date
     *            date
     * @param format
     *            "yyyy-MM-dd'T'HH:mm:ss'Z'"
     * @return String
     */
    public static String getTimeString(Date date, String format, String timeZone) {
        if (null == date || StringUtils.isEmpty(format)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        if (StringUtils.isNotEmpty(timeZone)) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        String str = dateFormat.format(date);
        return str;
    }
    /**
     * 获取不同风格的月份表示
     * @param calendar
     * @param style Calendar.LONG,Calendar.SHORT
     * @param locale
     * @return
     */
    public static String getMonthString(Calendar calendar,int style, Locale locale){
        if (null == calendar) {
            return null;
        }
        return calendar.getDisplayName(Calendar.MONTH, style, locale);
    }
    /**
     * 
     * @discription 获取UTC时间
     * @author rock 创建时间 2014年2月13日 下午3:31:28
     * @param dateStr
     *            dateStr
     * @param format
     *            format
     * @return long
     * @throws java.text.ParseException
     */
    public static long formatDateToLong(String dateStr, String format, String timeZone) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        if (StringUtils.isNotEmpty(timeZone)) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        }
        return dateFormat.parse(dateStr).getTime();
    }

    /**
     * 格式化字符串为指定要显示的日期格式字符串
     *
     * @param dateStr
     *            dateStr
     * @param format
     *            format
     * @return String
     */
    public static String formatDate(String dateStr, String format) {
        if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(format)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(dateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param date
     *            date
     * @param format
     *            "yyyy-MM-dd"
     * @return String
     */
    public static String getTimeNow(Date date, String format) {
        if (null == date || StringUtils.isEmpty(format)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        String str = dateFormat.format(date);
        return str;
    }

    /**
     * 将UTC时间转为指定格式的时间字符串
     *
     * @param time
     *            utc时间
     * @param pattern
     *            pattern
     * @return String
     */
    public static String utcToDateStr(Long time, String pattern) {
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
