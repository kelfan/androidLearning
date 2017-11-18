package com.example.chaofanz.mycalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/11/17.
 */

public class Database extends SQLiteOpenHelper {
    //database
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "MYCALENDAR";

    //table
    private static final String TABLE_NAME = "Events";            // it is the table to store information of events
    private static final String EVENT_ID = "Event_Id";            // each event has a unique id
    private static final String EVENT_CONTENT = "Event_Content"; // to record the content of event
    private static final String EVENT_DETAIL = "Event_Detail";   // add more content if need more details
    private static final String EVENT_LOCATION = "Event_Location";// stored the locations where the Event actually occur
    private static final String EVENT_LEVEL = "Event_Level";        // stored the level of the Event to filter the Event from small things; the higher the number the bigger the things
    private static final String PLAN_START = "Plan_Start";        // to record the datetime of event start; 00:00 means whole day event
    private static final String PLAN_END = "Plan_End";            //  to record the datetime of event end
    private static final String CREATE_DATE = "Create_Date";      // automatically record the datetime of event create
    private static final String COMPLETED_DATE = "Complete_Date"; // record date of complete
    private static final String EVENT_GENRE = "Event_Genre";      // put events into different level or category like Life or Class412
    private static final String EVENT_STATUS = "Event_Status";    // put event into different status like 0 uncategories, 1 scheduled, 2 done, 3 delete, 4 working, 5 important, 6 repeat, 7 waiting, 8 keep, 9 others, 10 lunar calendar
    private static final String REPEAT_TYPE = "Repeat_Type";      // event can be repeated by 1 yearly, 2 monthly, 3 weekly, 4 each workday, more number means repeat several days in a week like 13 means repeat each Monday & Wednesday and 234  means repeat on Mon,Tue & Wed


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PLAN_START + " TEXT, "
                + PLAN_END + " TEXT, "
                + EVENT_CONTENT + " TEXT, "
                + EVENT_DETAIL + " TEXT, "
                + EVENT_LOCATION + " TEXT, "
                + EVENT_LEVEL + " INTEGER, "
                + CREATE_DATE + " TEXT, "
                + COMPLETED_DATE + " TEXT, "
                + EVENT_GENRE + " TEXT, "
                + EVENT_STATUS + " INTEGER, "
                + REPEAT_TYPE + " INTEGER, "
                + ");";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    // CRUD Events
    public void addEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_ID,event.getId());
        values.put(PLAN_START,event.getPlan_start().toString());
        values.put(PLAN_END,event.getPlan_end().toString());
        values.put(EVENT_CONTENT,event.getContent());
        values.put(EVENT_DETAIL,event.getDetail());
        values.put(EVENT_LOCATION,event.getLocation());
        values.put(EVENT_LEVEL,event.getLevel());
        values.put(CREATE_DATE,event.getCreated().toString());
        values.put(COMPLETED_DATE,event.getCompleted().toString());
        values.put(EVENT_GENRE,event.getGenre());
        values.put(EVENT_STATUS,event.getStatus());
        values.put(REPEAT_TYPE,event.getRepeat_type());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int updateEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EVENT_ID,event.getId());
        values.put(PLAN_START,event.getPlan_start().toString());
        values.put(PLAN_END,event.getPlan_end().toString());
        values.put(EVENT_CONTENT,event.getContent());
        values.put(EVENT_DETAIL,event.getDetail());
        values.put(EVENT_LOCATION,event.getLocation());
        values.put(EVENT_LEVEL,event.getLevel());
        values.put(CREATE_DATE,event.getCreated().toString());
        values.put(COMPLETED_DATE,event.getCompleted().toString());
        values.put(EVENT_GENRE,event.getGenre());
        values.put(EVENT_STATUS,event.getStatus());
        values.put(REPEAT_TYPE,event.getRepeat_type());

        return db.update(TABLE_NAME,values,EVENT_ID+" =?", new String[]{String.valueOf(event.getId())});
    }

    public void deleteEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, EVENT_ID+" =?", new String[]{String.valueOf(event.getId())});
        db.close();
    }

    public void getEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{EVENT_ID,EVENT_CONTENT,EVENT_STATUS,
                        EVENT_DETAIL,EVENT_LEVEL,EVENT_LOCATION,
                        EVENT_GENRE, PLAN_END, PLAN_START,
                        REPEAT_TYPE, CREATE_DATE, COMPLETED_DATE},
                EVENT_ID+"=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        if (cursor != null)
            cursor.moveToFirst();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Event event = new Event(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),
                    cursor.getInt(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getString(7),dateFormat.parse(cursor.getString(8)),
                    dateFormat.parse(cursor.getString(9)),dateFormat.parse(cursor.getString(10)),dateFormat.parse(cursor.getString(11))
                    );
            return event;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
