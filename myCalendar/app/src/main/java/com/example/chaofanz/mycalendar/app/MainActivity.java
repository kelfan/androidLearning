package com.example.chaofanz.mycalendar.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.chaofanz.mycalendar.R;
import com.example.chaofanz.mycalendar.adapter.EventListAdapater;
import com.example.chaofanz.mycalendar.bean.Event;
import com.example.chaofanz.mycalendar.utils.Constant;
import com.example.chaofanz.mycalendar.utils.DbManager;
import com.example.chaofanz.mycalendar.utils.MySqliteHelper;
import com.example.chaofanz.mycalendar.utils.TimeHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    private ListView eventLv;
    private SQLiteDatabase db;
    private int totalNum; // Variable for the total number Of records in current Context
    private int pageSize = 20; // total number Of records will be displayed in a page
    private int pageNum; // represent the number of page can be displayed
    private int currentPage=1; //Variable for current Page
    private List<Event> totalList; // List Of events from Database
    private EventListAdapater adapter;
    private Boolean isDivPage; // A Boolean Variable To check whether is there any further Page division

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= DbManager.getInstance(this);

        /*
        attach list Of Event into ListView eventLv
         */
        eventLv = (ListView) findViewById(R.id.eventLv);
        SQLiteDatabase db = helper.getWritableDatabase();
    }

    /**
     * insert data into datebase for testing
     * @param view
     */
    public void createDb(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        String currentDatetime = "'"+ TimeHandler.datetimeToString(Calendar.getInstance().getTime())+ "'";
        for (int i=1;i<=30;i++) {
            String sql = "insert into "+Constant.TABLE_NAME+" values(null,"
                    +currentDatetime+", "
                    +currentDatetime+", 'content', 'detail', 'Hobart', "
                    +i+", "
                    +currentDatetime+", "
                    +currentDatetime+", 'life',"
                    +i+","
                    +i+" )";
            db.execSQL(sql);
        }
        db.close();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnQuery:
                SQLiteDatabase db=helper.getReadableDatabase();
                Cursor cursor = db.query(Constant.TABLE_NAME,
                        null, null,
                        null, null,
                        null, null
                );
                List<Event> list = DbManager.cursorToList(cursor);
                for (Event event : list) {
                    Log.i("tag",event.toString());
                }
                db.close();
                break;
        }
    }
}
