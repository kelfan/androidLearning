package com.example.administrator.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLData;

/**
 * Created by Administrator on 2017/11/18.
 * 主要是对数据库操作的工作类
 * 单例模式
 */

public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if(helper==null){
            helper=new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句在数据库中执行语句
     * @param db 数据库对象
     * @param sql sql语句
     */
    public static void execSQL(SQLiteDatabase db,String sql){
        if(db!=null){
            if (sql!=null && !"".equals(sql)){
                db.execSQL(sql);
            }
        }
    }
}
