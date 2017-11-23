package com.example.chaofanz.mycalendar.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chaofanz.mycalendar.bean.Event;

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
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VER);

    }


    // id, start, end, content, detail, location, level, create, complete, genre, status, repeat
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

    // id, start, end, content, detail, location, level, create, complete, genre, status, repeat
    public void addEvent(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.EVENT_CONTENT, content);
        values.put(Constant.EVENT_LEVEL, 0);
        values.put(Constant.CREATE_DATE, TimeHandler.getCurrentDateTimeString());
        values.put(Constant.EVENT_GENRE, "others");
        values.put(Constant.EVENT_STATUS,0);
        values.put(Constant.REPEAT_TYPE,0);
        long result = db.insert(Constant.TABLE_NAME,null,values);
        db.close();
//        return result;
    }

    public int updateEvent(Event event) {
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

        return db.update(Constant.TABLE_NAME, values, Constant.EVENT_ID + " =?", new String[]{String.valueOf(event.getId())});
    }

    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constant.TABLE_NAME, Constant.EVENT_ID + " =?", new String[]{String.valueOf(event.getId())});
        db.close();
    }

    public Event getEvent(int id) {
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

        try {
            Event event = new Event(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2),
                    cursor.getInt(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7),
                    TimeHandler.stringToDatetime(cursor.getString(8)),
                    TimeHandler.stringToDatetime(cursor.getString(9)),
                    TimeHandler.stringToDatetime(cursor.getString(10)),
                    TimeHandler.stringToDatetime(cursor.getString(11))
            );
            return event;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
