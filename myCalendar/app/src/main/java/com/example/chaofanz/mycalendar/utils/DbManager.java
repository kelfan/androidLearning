package com.example.chaofanz.mycalendar.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.chaofanz.mycalendar.bean.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20.
 */

public class DbManager {
    // provide a singleton of MySqliteHelper
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context) {
        if(helper==null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * execute Sql sentence in Database
     * @param db Database object
     * @param sql Sql sentence
     */
    public static void execSQL(SQLiteDatabase db, String sql) {
        if (db != null) {
            if (sql != null && !"".equals(sql)) {
                db.execSQL(sql);
            }
        }
    }

    /**
     * acquire cursor data based on Sql sentence
     * @param db Database object
     * @param sql SQL sentence
     * @param selectionArgs the Value of Sql sentence placeholder
     * @return date result in cursor
     */
    public static Cursor selectDataBySql(SQLiteDatabase db,
                                         String sql,
                                         String[] selectionArgs){
        Cursor cursor = null;
        if (db != null) {
            cursor=db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    /**
     *  translate Cursor object into Event
     * @param cursor the Cursor need to be translated
     * @return the list after translation
     */
    public static Event cursorToEvent(Cursor cursor) {
        try{
            int _id = cursor.getInt(cursor.getColumnIndex(Constant.EVENT_ID));
            int status = cursor.getInt(cursor.getColumnIndex(Constant.EVENT_STATUS));
            int repeat_type = cursor.getInt(cursor.getColumnIndex(Constant.REPEAT_TYPE));
            int level = cursor.getInt(cursor.getColumnIndex(Constant.EVENT_LEVEL));

            String content = cursor.getString(cursor.getColumnIndex(Constant.EVENT_CONTENT));
            String detail = cursor.getString(cursor.getColumnIndex(Constant.EVENT_DETAIL));
            String genre = cursor.getString(cursor.getColumnIndex(Constant.EVENT_GENRE));
            String location = cursor.getString(cursor.getColumnIndex(Constant.EVENT_LOCATION));

            String start_str = cursor.getString(cursor.getColumnIndex(Constant.PLAN_START));
            String end_str = cursor.getString(cursor.getColumnIndex(Constant.PLAN_END));
            String create_str = cursor.getString(cursor.getColumnIndex(Constant.CREATE_DATE));
            String complete_str = cursor.getString(cursor.getColumnIndex(Constant.COMPLETED_DATE));

            Date plan_start = null;
            Date plan_end = null;
            Date created = null;
            Date completed = null;

            if (!"".equals(start_str)&& start_str != null) {
                plan_start = TimeHandler.stringToDatetime(start_str);
            }
            if (!"".equals(end_str) && end_str != null) {
                plan_end = TimeHandler.stringToDatetime(end_str);
            }
            if (!"".equals(create_str)&& create_str != null) {
                created = TimeHandler.stringToDatetime(create_str);
            }
            if (!"".equals(complete_str)&& complete_str != null) {
                completed = TimeHandler.stringToDatetime(complete_str);
            }

            Event event = new Event(_id, status, repeat_type,
                    level, content, detail, genre, location,
                    plan_start, plan_end, created, completed);
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Test/DbManager","wrong dateformat in parsing in DbManager");
            return null;
        }
    }

    /**
     *  translate Cursor object into List
     * @param cursor the Cursor need to be translated
     * @return the list after translation
     */
    public static List<Event> cursorToList(Cursor cursor){
            List<Event> list = new ArrayList<>();
            while (cursor.moveToNext()) {
                Event event = cursorToEvent(cursor);
                list.add(event);
            }
            return list;
    }

    public static int getDataCount(String tablename){
        SQLiteDatabase db = helper.getWritableDatabase();
        return getDataCount(db,tablename);
    }

    /**
     * acquire the total number of records based on database and tablename
     * @param db the database object
     * @param tablename the tablename
     * @return the totoal number of records
     */
    public static int getDataCount(SQLiteDatabase db, String tablename) {
        int count=0;
        if (db != null) {
//            Cursor cursor=db.rawQuery("select * from "+tablename,null);
            Cursor cursor=db.query(tablename,null,null,null,null,null,null);
            count=cursor.getCount(); //acquire the total number of records in the database
        }
        return count;
    }

    /**
     * aquire the required data based on the current page
     * @param tablename the tablename
     * @param CurrentPage the page number of current page
     * @param pageSize the number of records in each page
     * @return the dataset to display in the current page
     */
    public static List<Event> getListByCurrentPage(String tablename,int CurrentPage, int pageSize){
        SQLiteDatabase db = helper.getWritableDatabase();
        int index=(CurrentPage-1)*pageSize; // acquire the index of first record of current page
        Cursor cursor=null;
        if (db!=null){
            cursor = db.query(tablename,null,null,null,null,null,Constant.EVENT_ID+" desc", index+","+pageSize);
        }
        List<Event> list = cursorToList(cursor);
        db.close();
        return list;
    }

    public static long addEvent(String content, String genre, String detail,
                                String start, String end,
                                String location, String complete,
                                int level, int status, int repeat){
        return helper.addEvent(content, genre, start, end, detail, location, complete, level, status, repeat);
    }

    public static long addEvent(String content, String genre, String detail,
                                String startDate, String startTime,
                                String endDate,String endTime,
                                String location, String complete,
                                int level, int status, int repeat){
        String start = startDate;
        if (!"".equals(startDate) && startDate != null) {
            if ("".equals(startTime) && startTime != null) {
                start = startDate + " 00:00:00 " + TimeHandler.getTimezoneString();
            } else {
                start = startDate + " "+ startTime + ":00 "+TimeHandler.getTimezoneString();
            }
        }
        String end = endDate;
        if (!"".equals(endDate) && endDate != null) {
            if ("".equals(endTime) && endTime != null ) {
                end = endDate + " 00:00:00 " + TimeHandler.getTimezoneString();
            } else {
                end = endDate + " "+ endTime + ":00 "+TimeHandler.getTimezoneString();
            }
        }
        return helper.addEvent(content, genre, start, end, detail, location, complete, level, status, repeat);
    }

    public static Event getLastEvent(String tablename){
        return helper.getLastEvent(tablename);
    }

    /**
     * insert data into datebase for testing
     * @param view
     */
    public static void createTestDataset(View view){
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

    public static Event getEventById(int id){
        return helper.getEventById(id);
    }

    public static long updateEvent( int id, String content,String genre,
                                  String start, String end,
                                  String detail,
                                  String location,
                                  String complete,
                                  int level, int status, int repeat){
        long result = helper.updateEvent(id, content, genre, start, end, detail, location, complete, level, status, repeat);
        return result;
    }

    public static long updateEvent(Event event) {
        return helper.updateEvent(event);
    }

    public static int deleteEvent(Event event) {
        if (event != null) {
            return helper.deleteEvent(event);
        }
        return -2;
    }
}

