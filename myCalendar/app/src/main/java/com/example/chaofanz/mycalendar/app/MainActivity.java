package com.example.chaofanz.mycalendar.app;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chaofanz.mycalendar.R;
import com.example.chaofanz.mycalendar.adapter.EventListAdapater;
import com.example.chaofanz.mycalendar.bean.Event;
import com.example.chaofanz.mycalendar.utils.Constant;
import com.example.chaofanz.mycalendar.utils.DbManager;
import com.example.chaofanz.mycalendar.utils.MySqliteHelper;
import com.example.chaofanz.mycalendar.utils.TimeHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private MySqliteHelper helper;
    private ListView eventLv;
    private int totalNum; // Variable for the total number Of records in current Context
    private int pageSize = 20; // total number Of records will be displayed in a page
    private int pageNum; // represent the number of page can be displayed
    private int currentPage=1; //Variable for current Page
    private List<Event> totalList; // List Of events from Database
    private EventListAdapater adapter;
    private Boolean isDivPage; // A Boolean Variable To check whether is there any further Page division
    EditText edtContent,edtDetail,edtDate,edtTime;
    private int day,month,year,hour,minute; // Variable To record Date and Time

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
        // acquire the total number Of records in the Database
        totalNum = DbManager.getDataCount(Constant.TABLE_NAME);
        // acquire the total number Of pages based on the total number Of records & number of records in each Page
        pageNum = (int) Math.ceil(totalNum/(double)pageSize);
        if (currentPage == 1) {
            totalList = DbManager.getListByCurrentPage(
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
                        totalList.addAll(DbManager.getListByCurrentPage(Constant.TABLE_NAME,
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

        eventLv.setOnItemClickListener(this);

    }

    /**
     * insert data into datebase for testing
     * @param view
     */
    public void createDb(View view){
        DbManager.createTestDataset(view);
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
                String detail = edtDetail.getText().toString();
                String startDate = edtDate.getText().toString();
                String startTime = edtTime.getText().toString();
                if (!"".equals(startDate) && TimeHandler.verifyDate(startDate) == -1) {
                    Toast.makeText(MainActivity.this, "date format should be yyyy-MM-dd", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!"".equals(startTime) && TimeHandler.verifyTime(startTime) == -1) {
                    Toast.makeText(MainActivity.this, "time format should be HH:mm", Toast.LENGTH_SHORT).show();
                    break;
                }

                if (!"".equals(content)) {
                    long result = DbManager.addEvent(content,null,detail,startDate,startTime,null,null,null,null,0,0,0);
                    if (result > 0) {
                        Toast.makeText(MainActivity.this, "insert data successfully", Toast.LENGTH_SHORT).show();
                        Collections.reverse(totalList);
                        totalList.add(DbManager.getLastEvent(Constant.TABLE_NAME));
                        Collections.reverse(totalList);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "fail to insert data", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Content cannot be empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnDate:
                Calendar c = Calendar.getInstance();
                day = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH);
                year = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        edtDate.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
                break;
            case R.id.btnTime:
                c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        edtTime.setText(hourOfDay+":"+minutes);
                    }
                },hour,minute,false);
                timePickerDialog.show();
                break;
            case R.id.btnNew:
                Intent itemIntent = new Intent(MainActivity.this, AddActivity.class);
                itemIntent.putExtra(Constant.EVENT_ITEM_INTENT, -1);
                startActivity(itemIntent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent itemIntent = new Intent(MainActivity.this, AddActivity.class);
        itemIntent.putExtra(Constant.EVENT_ITEM_INTENT, (int) id);
        startActivity(itemIntent);
    }
}
