package com.example.chaofanz.datepickertimepickerdemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/*
references:
https://www.youtube.com/watch?v=y3exATaC0kA
https://www.youtube.com/watch?v=hwe1abDO2Ag
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_date, btn_time, btn_date2;
    EditText edt_date, edt_time;
    private int day,month,year,hour,minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_date = (Button) findViewById(R.id.btn_date);
        btn_time = (Button) findViewById(R.id.btn_time);
        edt_date = (EditText) findViewById(R.id.edt_date);
        edt_time = (EditText) findViewById(R.id.edt_time);

        btn_date.setOnClickListener(this);
        btn_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btn_date){
            final Calendar c = Calendar.getInstance();
            day = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH);
            year = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    edt_date.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            },year,month,day);
            datePickerDialog.show();
        }
        if (view == btn_time) {
            final  Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minutes = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                    edt_time.setText(hourOfDay+":"+minutes);
                }
            },hour,minutes,false);
            timePickerDialog.show();
        }
//        if (view == btn_date2) {
//            final  Calendar c = Calendar.getInstance();
//            day = c.get(Calendar.DAY_OF_MONTH);
//            month = c.get(Calendar.MONTH);
//            year = c.get(Calendar.YEAR);
//
//            DatePickerDialog dateDialog = new DatePickerDialog(
//                    MainActivity.this,
//                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
//                    new DatePickerDialog.OnDateSetListener() {
//                        @Override
//                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                            month = month + 1;
//                            String date = month + "/" + day + "/" + year;
//                            edt_date.setText(date);
//                        }
//                    }, year, month, day
//            );
//            dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dateDialog.show();
//        }
    }


    public void date2Click(View view) {
        final  Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);

        DatePickerDialog dateDialog = new DatePickerDialog(
                MainActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = month + "/" + day + "/" + year;
                        edt_date.setText(date);
                    }
                }, year, month, day
        );
        dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dateDialog.show();
    }
}
