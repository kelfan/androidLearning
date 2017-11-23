package com.example.chaofanz.mycalendar.app;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
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
    EditText edtContent,edtDetail,edtDate,edtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= DbManager.getInstance(this);

        edtContent = findViewById(R.id.edtContent);
        edtDetail = findViewById(R.id.edtDetail);
        edtDate = findViewById(R.id.edtDate);
        edtTime = findViewById(R.id.edtTime);

        /*
        attach list Of Event into ListView eventLv
         */
        eventLv = (ListView) findViewById(R.id.eventLv);
        db = helper.getWritableDatabase();
        // acquire the total number Of records in the Database
        totalNum = DbManager.getDataCount(db, Constant.TABLE_NAME);
        // acquire the total number Of pages based on the total number Of records & number of records in each Page
        pageNum = (int) Math.ceil(totalNum/(double)pageSize);
        if (currentPage == 1) {
            totalList = DbManager.getListByCurrentPage(db,
                    Constant.TABLE_NAME, currentPage, pageSize);
        }
        adapter = new EventListAdapater(this, totalList);
        eventLv.setAdapter(adapter);

        eventLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // AbsListView.OnScrollListener.SCROLL_STATE_IDLE means the status when the scroll is stoped
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
                    if (currentPage < pageNum) {
                        currentPage++;
                        // add new dataset into the list based on the new Page number
                        totalList.addAll(DbManager.getListByCurrentPage(db, Constant.TABLE_NAME,
                                currentPage, pageSize));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView,
                                 int firstVisibleItem,
                                 int visibleItemCount,
                                 int totalItemCount) {
                isDivPage = ((firstVisibleItem + visibleItemCount) == totalItemCount);
            }
        });
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
            case R.id.btnAdd:
                String content = edtContent.getText().toString();
                helper.addEvent(content);
                break;
        }
    }
}
