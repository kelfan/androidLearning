package com.example.chaofanz.mycalendar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/11/20.
 */

public class TimeHandler {
    public static String datetimeToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATETIME_FORMAT);
        String result = dateFormat.format(date);
        return result;
    }

    public static Date stringToDatetime(String str) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat(Constant.DATETIME_FORMAT);
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.parse(str);
    }
}
