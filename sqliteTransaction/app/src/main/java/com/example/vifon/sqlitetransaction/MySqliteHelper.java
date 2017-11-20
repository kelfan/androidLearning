package com.example.vifon.sqlitetransaction;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Administrator on 2017/11/18.
 *
 */

public class MySqliteHelper extends SQLiteOpenHelper {

    /**
     * 构造函数
     * @param context 上下文对象
     * @param name 表示创建数据库名称
     * @param factory 游标工厂
     * @param version 表示创建数据库的版本 >=1
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }

    /**
     * 当数据库创建时回调的函数
     * @param sqLiteDatabase 表示数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("tag", "-----------onCreate--------");
        String sql = "create table "+Constant.TABLE_NAME +"("
                + Constant._ID+" Integer primary key,"
                + Constant.NAME+" varchar(10), "
                + Constant.AGE+" Integer"
                + ")";
        sqLiteDatabase.execSQL(sql); //执行sql语句
    }

    /**
     * 当数据库版本更新时回调的函数
     * @param sqLiteDatabase 表示数据库对象
     * @param i 表示数据库旧版本
     * @param i1 表示数据库新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("tag", "-----------onUpgrade--------");
    }

    /**
     * 当数据库打开回调的函数
     * @param db 数据库对象
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("tag", "-----------onOpen--------");
    }
}
