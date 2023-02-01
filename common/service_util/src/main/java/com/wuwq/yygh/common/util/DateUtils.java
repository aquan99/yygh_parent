package com.wuwq.yygh.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.common.util
 * @ClassName:DateUtils
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-21 14:39
 * @Version: 1.0
 */
public class DateUtils {

    public static Date getAfterDate(int amount) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH, amount);
        return instance.getTime();
    }

    public static Date ConvertTime(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.parse(time);
    }

    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    public static String getDateStr(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(date == null){
            return null;
        }
        return dateFormat.format(date);
    }

    public static Date parseDate(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(dateStr);
    }

    public static Date ConvertBirth(String birthStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.parse(birthStr);
    }

    public static String getDateTimeStr(Date dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(dateTime == null){
            return null;
        }
        return dateFormat.format(dateTime);
    }

    public static Boolean date1LeDate2(Date date1, Date date2) throws ParseException {
        String dateStr1 = getDateStr(date1);
        String dateStr2 = getDateStr(date2);
        date1 = parseDate(dateStr1, "yyyy-MM-dd");
        date2 = parseDate(dateStr2, "yyyy-MM-dd");
        return date1.getTime()<=date2.getTime();
    }
}
