package com.hdg.rjra.rdb.proxy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rock on 2014/10/25.
 */
public class DateUtils {
    /**
     *
     * @discription 格式化时间
     * @param date date
     * @param type type
     * @param offset offset
     * @param format format
     * @return String
     */
    public static String getDate(Date date, int type, int offset, String format) {
        Calendar time = Calendar.getInstance();
        time.setTime(date);
        time.add(type, offset);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time.getTime());
    }

    /**
     * 传递给前几天
     * 参数说明
     * 昨天：-1 前天 -2
     * 明天：1      后天 2
     *
     * @param beforeDays
     * @return
     */
    public static Date distanceTodayFormatDate(int beforeDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, beforeDays);
        cal.get(Calendar.YEAR);
        return cal.getTime();
    }
}
