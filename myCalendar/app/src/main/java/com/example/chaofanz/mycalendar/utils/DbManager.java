package com.example.chaofanz.mycalendar.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chaofanz.mycalendar.bean.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     *  translate Cursor object into List
     * @param cursor the Cursor need to be translated
     * @return the list after translation
     */
    public static List<Event> cursorToList(Cursor cursor){
        try{
            List<Event> list = new ArrayList<>();
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(cursor.getColumnIndex(Constant.EVENT_ID));
                int status = cursor.getInt(cursor.getColumnIndex(Constant.EVENT_STATUS));
                int repeat_type = cursor.getInt(cursor.getColumnIndex(Constant.REPEAT_TYPE));
                int level = cursor.getInt(cursor.getColumnIndex(Constant.EVENT_LEVEL));

                String content = cursor.getString(cursor.getColumnIndex(Constant.EVENT_CONTENT));
                String detail = cursor.getString(cursor.getColumnIndex(Constant.EVENT_DETAIL));
                String genre = cursor.getString(cursor.getColumnIndex(Constant.EVENT_GENRE));
                String location = cursor.getString(cursor.getColumnIndex(Constant.EVENT_LOCATION));

                SimpleDateFormat dateFormat= new SimpleDateFormat(Constant.DATETIME_FORMAT);

                    Date plan_start = dateFormat.parse(cursor.getString(cursor.getColumnIndex(Constant.PLAN_START)));
                    Date plan_end = dateFormat.parse(cursor.getString(cursor.getColumnIndex(Constant.PLAN_END)));
                    Date created = dateFormat.parse(cursor.getString(cursor.getColumnIndex(Constant.CREATE_DATE)));
                    Date completed = dateFormat.parse(cursor.getString(cursor.getColumnIndex(Constant.COMPLETED_DATE)));

                Event event = new Event(_id,status,repeat_type,
                        level,content,detail,genre,location,
                        plan_start,plan_end,created,completed);
                list.add(event);
            }
            return list;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
