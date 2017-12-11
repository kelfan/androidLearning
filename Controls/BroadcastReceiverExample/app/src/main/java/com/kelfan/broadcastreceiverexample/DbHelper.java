package com.kelfan.broadcastreceiverexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.WifiManager;

/**
 * Created by Administrator on 11/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "numberDb";
    public static final int DATABASE_VERSION = 1;
    public static final String CREATE = "create table "+ DbContract.TABLE_NAME +
            "(id integer primary key autoincrement," + DbContract.INCOMING_NUMBER + " text);";
    public static final String DROP_TABLE = "drop table if exists" + DbContract.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void saveNumber(String number, SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.INCOMING_NUMBER,number);
        sqLiteDatabase.insert(DbContract.TABLE_NAME, null, contentValues);
    }

    public Cursor readNumber(SQLiteDatabase sqLiteDatabase) {
        String[] projection = {"id", DbContract.INCOMING_NUMBER};
        return (sqLiteDatabase.query(DbContract.TABLE_NAME, projection, null, null, null, null, null));
    }
}
