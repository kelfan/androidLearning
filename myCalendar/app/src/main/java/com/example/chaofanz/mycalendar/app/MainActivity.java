package com.example.chaofanz.mycalendar.app;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.chaofanz.mycalendar.R;
import com.example.chaofanz.mycalendar.utils.Constant;
import com.example.chaofanz.mycalendar.utils.DbManager;
import com.example.chaofanz.mycalendar.utils.MySqliteHelper;
import com.example.chaofanz.mycalendar.utils.TimeHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= DbManager.getInstance(this);
    }

    public void createDb(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        String currentDatetime = TimeHandler.datetimeToString(Calendar.getInstance().getTime());
        for (int i=1;i<=30;i++) {
            String sql = "insert into Event values(null, )"
        }
        db.close();
    }
}
