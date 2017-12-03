package com.example.chaofanz.mycalendar.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import com.example.chaofanz.mycalendar.bean.Event;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/11/20.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
//        super(context, Environment.getExternalStorageDirectory()+ File.separator+ "05fanNotes"+ File.separator + Constant.DATABASE_NAME, null, Constant.DATABASE_VER);
        super(context, System.getenv("SECONDARY_STORAGE")+ File.separator+ "05fanNotes"+ File.separator+ "calendarDb"+ File.separator + Constant.DATABASE_NAME, null, Constant.DATABASE_VER);

    }


    // id, start, end, content, detail, location, level, create, complete, genre, Status, repeat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + Constant.TABLE_NAME + "("
                + Constant.EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Constant.PLAN_START + " TEXT, "
                + Constant.PLAN_END + " TEXT, "
                + Constant.EVENT_CONTENT + " TEXT, "
                + Constant.EVENT_DETAIL + " TEXT, "
                + Constant.EVENT_LOCATION + " TEXT, "
                + Constant.EVENT_LEVEL + " INTEGER, "
                + Constant.CREATE_DATE + " TEXT, "
                + Constant.COMPLETED_DATE + " TEXT, "
                + Constant.EVENT_GENRE + " TEXT, "
                + Constant.EVENT_STATUS + " INTEGER, "
                + Constant.REPEAT_TYPE + " INTEGER "
                + ")";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_NAME);
    }

    // CRUD Events
    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.EVENT_ID, event.getId());
        values.put(Constant.PLAN_START, event.getPlan_start().toString());
        values.put(Constant.PLAN_END, event.getPlan_end().toString());
        values.put(Constant.EVENT_CONTENT, event.getContent());
        values.put(Constant.EVENT_DETAIL, event.getDetail());
        values.put(Constant.EVENT_LOCATION, event.getLocation());
        values.put(Constant.EVENT_LEVEL, event.getLevel());
        values.put(Constant.CREATE_DATE, event.getCreated().toString());
        values.put(Constant.COMPLETED_DATE, event.getCompleted().toString());
        values.put(Constant.EVENT_GENRE, event.getGenre());
        values.put(Constant.EVENT_STATUS, event.getStatus());
        values.put(Constant.REPEAT_TYPE, event.getRepeat_type());

        db.insert(Constant.TABLE_NAME, null, values);
        db.close();
    }

    // id, start, end, content, detail, location, level, create, complete, genre, Status, repeat
    public long addEvent(String content,String genre,
                         String start, String end,
                         String detail,
                         String location,
                         String complete,
                         int level, int status, int repeat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.EVENT_CONTENT, content);
        values.put(Constant.EVENT_LEVEL, level);
        values.put(Constant.CREATE_DATE, TimeHandler.getCurrentDateTimeString());
        if (genre == null){
            genre = "others";
        }
        values.put(Constant.EVENT_GENRE, genre);
        values.put(Constant.EVENT_STATUS,status);
        values.put(Constant.REPEAT_TYPE,repeat);
        if (start != null && !"".equals(start)){
            if (TimeHandler.verifyDateTime(start) == -1) {
                return -1;
            }
            values.put(Constant.PLAN_START,start);
            if (end == null){
                end = start;
            }
        }
        if (end != null && !"".equals(end)){
            if (TimeHandler.verifyDateTime(end) == -1 ){
                return -1;
            }
            values.put(Constant.PLAN_END,end);
        }
        if (detail != null) {
            values.put(Constant.EVENT_DETAIL,detail);
        }
        if (location != null) {
            values.put(Constant.EVENT_LOCATION,location);
        }
        if (complete != null) {
            values.put(Constant.COMPLETED_DATE,complete);
        }
        long result = db.insert(Constant.TABLE_NAME,null,values);
        db.close();
        return result;
    }

    public long updateEvent(int id, String content,String genre,
                         String start, String end,
                         String detail,
                         String location,
                         String complete,
                         int level, int status, int repeat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = processEvent(content, genre, start, end, detail, location, complete, level, status, repeat);
        long result = db.update(Constant.TABLE_NAME, values, Constant.EVENT_ID + " =?", new String[]{ Integer.toString(id) });
        db.close();
        return result;
    }

    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.EVENT_ID, event.getId());
        values.put(Constant.PLAN_START, TimeHandler.datetimeToString(event.getPlan_start()));
        values.put(Constant.PLAN_END, TimeHandler.datetimeToString(event.getPlan_end()));
        values.put(Constant.EVENT_CONTENT, event.getContent());
        values.put(Constant.EVENT_DETAIL, event.getDetail());
        values.put(Constant.EVENT_LOCATION, event.getLocation());
        values.put(Constant.EVENT_LEVEL, event.getLevel());
        values.put(Constant.CREATE_DATE, TimeHandler.datetimeToString(event.getCreated()));
        values.put(Constant.COMPLETED_DATE, TimeHandler.datetimeToString(event.getCompleted()));
        values.put(Constant.EVENT_GENRE, event.getGenre());
        values.put(Constant.EVENT_STATUS, event.getStatus());
        values.put(Constant.REPEAT_TYPE, event.getRepeat_type());

        return db.update(Constant.TABLE_NAME, values, Constant.EVENT_ID + " =?", new String[]{String.valueOf(event.getId())});
    }

    public int deleteEvent(Event event) {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Constant.TABLE_NAME, Constant.EVENT_ID + " =?", new String[]{String.valueOf(event.getId())});
            db.close();
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    public Event getEventById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                Constant.TABLE_NAME,
                new String[]{Constant.EVENT_ID, Constant.EVENT_CONTENT, Constant.EVENT_STATUS,
                        Constant.EVENT_DETAIL, Constant.EVENT_LEVEL, Constant.EVENT_LOCATION,
                        Constant.EVENT_GENRE, Constant.PLAN_END, Constant.PLAN_START,
                        Constant.REPEAT_TYPE, Constant.CREATE_DATE, Constant.COMPLETED_DATE},
                Constant.EVENT_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        if (cursor != null)
            cursor.moveToFirst();
            Event event = DbManager.cursorToEvent(cursor);
            return event;
    }

    public Event getLastEvent(String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                tablename,
                new String[]{Constant.EVENT_ID, Constant.EVENT_CONTENT, Constant.EVENT_STATUS,
                        Constant.EVENT_DETAIL, Constant.EVENT_LEVEL, Constant.EVENT_LOCATION,
                        Constant.EVENT_GENRE, Constant.PLAN_END, Constant.PLAN_START,
                        Constant.REPEAT_TYPE, Constant.CREATE_DATE, Constant.COMPLETED_DATE},
                null,
                null,
                null,
                null,
                Constant.EVENT_ID +" desc",
                "1"
        );
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Event event = DbManager.cursorToEvent(cursor);
            return event;
        }
        return null;
    }

    // add string into values to put into event
    public ContentValues processEvent(String content,String genre,
                                      String start, String end,
                                      String detail,
                                      String location,
                                      String complete,
                                      int level, int status, int repeat) {
        ContentValues values = new ContentValues();
        values.put(Constant.EVENT_CONTENT, content);
        values.put(Constant.EVENT_LEVEL, level);
        values.put(Constant.CREATE_DATE, TimeHandler.getCurrentDateTimeString());
        if (genre == null){
            genre = "others";
        }
        values.put(Constant.EVENT_GENRE, genre);
        values.put(Constant.EVENT_STATUS,status);
        values.put(Constant.REPEAT_TYPE,repeat);
        if (start != null && !"".equals(start)){
            if (TimeHandler.verifyDateTime(start) == -1) {
                return null;
            }
            values.put(Constant.PLAN_START,start);
            if (end == null){
                end = start;
            }
        }
        if (end != null && !"".equals(end)){
            if (TimeHandler.verifyDateTime(end) == -1 ){
                return null;
            }
            values.put(Constant.PLAN_END,end);
        }
        if (detail != null) {
            values.put(Constant.EVENT_DETAIL,detail);
        }
        if (location != null) {
            values.put(Constant.EVENT_LOCATION,location);
        }
        if (complete != null) {
            values.put(Constant.COMPLETED_DATE,complete);
        }
        return values;
    }
}
