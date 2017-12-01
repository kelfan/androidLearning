package com.example.chaofanz.mycalendar.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.chaofanz.mycalendar.R;
import com.example.chaofanz.mycalendar.bean.Event;
import com.example.chaofanz.mycalendar.utils.Constant;
import com.example.chaofanz.mycalendar.utils.DbManager;

/**
 * Created by Administrator on 2017/11/26.
 */

public class AddActivity extends AppCompatActivity {
    Button btnAddUpdate, btnStartDate, btnStartTime, btnEndDate, btnEndTime;
    EditText edtContent, edtDetail, edtGenre, edtStartDate, edtEndDate, edtStartTime, edtEndTime, edtLevel, edtLocation, edtRepeat;
    Spinner spinnerStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        // aqcuire resources from view
        btnAddUpdate = findViewById(R.id.BtnAddUpdate);
        btnStartDate = findViewById(R.id.btnStartDate);
        btnStartTime = findViewById(R.id.btnStartTime);
        btnEndDate = findViewById(R.id.btnEndDate);
        btnEndTime = findViewById(R.id.btnEndTime);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);
        edtContent = findViewById(R.id.edtContent);


        // acquire the event id from selected item of mainactivity's listview
        int eventId = getIntent().getExtras().getInt(Constant.EVENT_ITEM_INTENT);

        Event event = DbManager.getEventById(eventId);
        edtContent.setText(event.getContent());
    }
}
