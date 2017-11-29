package com.example.chaofanz.mycalendar.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    public static String getDayString(Date date){
        if (date!=null){
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd:HH");
            return dateFormat.format(date);
        }
        Log.i("My", "getDayString's date is null");
        return "";
    }

    public static String getCurrentDateTimeString(){
        return TimeHandler.datetimeToString(Calendar.getInstance().getTime());
    }

    public static Date getCurrentDateTime(){
        return Calendar.getInstance().getTime();
    }

    public static String getTimezoneString(){ return new SimpleDateFormat("z").format(Calendar.getInstance().getTime()); }
}
