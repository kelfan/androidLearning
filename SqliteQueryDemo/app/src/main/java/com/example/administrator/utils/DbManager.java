package com.example.administrator.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/18.
 */

public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if (helper==null){
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句查询获取cursor对象
     * @param db 数据库对象
     * @param sql 查询语句
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db,String sql, String[] selectionArgs){
        Cursor cursor=null;
        if (cursor!=null){
            cursor=db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    /**
     * 把Cursor转换为集合对象
     * @param cursor 需要转换的游标
     * @return 转换后的集合对象
     */
    public static List<Person> cursorToList(Cursor cursor){
        List<Person> list=new ArrayList<>();
        // moveToNext(A 如果为true表示还有下一条,否则读取完毕
        while(cursor.moveToNext()){
            // getColumnIndex(String columnName) 根据参数中指定的字段名称获取字段下标
            int columnIndex=cursor.getColumnIndex(Constant._ID);
            // getInt(int ColumnIndex) 根据指定字段下标获取对应int类型的Value
            int _id=cursor.getInt(columnIndex);

            String name=cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age=cursor.getInt(cursor.getColumnIndex(Constant.AGE));

            Person person=new Person(_id,name,age);
            list.add(person);
        }
        return list;
    }
}
