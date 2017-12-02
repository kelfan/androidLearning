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
import com.example.chaofanz.mycalendar.utils.TimeHandler;

import java.util.Date;

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
        edtDetail = findViewById(R.id.edtDetail);
        edtGenre = findViewById(R.id.edtGenre);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtStartTime = findViewById(R.id.edtStartTime);
        edtEndDate = findViewById(R.id.edtEndDate);
        edtEndTime = findViewById(R.id.edtEndTime);
        edtLevel = findViewById(R.id.edtLevel);
        edtLocation = findViewById(R.id.edtLocation);
        edtRepeat = findViewById(R.id.edtRepeat);
        spinnerStatus = findViewById(R.id.spinnerStatus);

        // acquire the event id from selected item of mainactivity's listview
        int eventId = getIntent().getExtras().getInt(Constant.EVENT_ITEM_INTENT);
        if (eventId > 0 ) {
//            set content text of AddActivity view
            Event event = DbManager.getEventById(eventId);
            edtContent.setText(event.getContent());
            edtDetail.setText(event.getDetail());
            edtGenre.setText(event.getGenre());
            edtStartDate.setText(TimeHandler.getDateString(event.getPlan_start()));
            edtStartTime.setText(TimeHandler.getTimeString(event.getPlan_start()));
            edtEndDate.setText(TimeHandler.getDateString(event.getPlan_end()));
            edtEndTime.setText(TimeHandler.getTimeString(event.getPlan_end()));
            edtLevel.setText(String.valueOf(event.getLevel()));
            edtLocation.setText(event.getLocation());
            edtRepeat.setText(String.valueOf(event.getRepeat_type()));
//            spinnerStatus.setText(event.getDetail());

        }
    }
}
