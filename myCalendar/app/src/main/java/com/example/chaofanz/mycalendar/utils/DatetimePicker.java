package com.example.chaofanz.mycalendar.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.chaofanz.mycalendar.bean.Event;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/12/3.
 */

public class DatetimePicker {

    public static void datePicker(final Context context, final TextView textView) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public static void timePicker(Context context, final TextView textView) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                textView.setText(hourOfDay+":"+minutes);
            }
        },hour,minute,false);
        timePickerDialog.show();
    }
}
