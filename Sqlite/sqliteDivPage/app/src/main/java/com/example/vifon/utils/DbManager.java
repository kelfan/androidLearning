package com.example.vifon.utils;

/**
 * Created by Administrator on 2017/11/20.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vifon.bean.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/18.
 */

public class DbManager {
    /**
     * 根据sql语句查询获取cursor对象
     * @param db 数据库对象
     * @param sql 查询语句
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db,String sql, String[] selectionArgs){
        Cursor cursor=null;
        if (db!=null){
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

    /**
     * 根据数据库以及数据表名称获取表中数据总条目
     * @param db 数据库对象
     * @param tablename 数据表名称
     * @return 数据总条目
     */
    public static int getDataCount(SQLiteDatabase db, String tablename) {
        int count=0;
        if (db != null) {
            Cursor cursor=db.rawQuery("select * from "+tablename,null);
            count=cursor.getCount(); //获取cursor中的数据总数
        }
        return count;
    }

    /**
     * 根据当前页码查询获取该页码对应的集合数据
     * @param db 数据库对象
     * @param tablename 数据表名称
     * @param CurrentPage 当前页码
     * @param pageSize 每页展示数据条目
     * @return 当前页对应D的集合

     */
    public static List<Person> getListByCurrentPage(SQLiteDatabase db,String tablename,int CurrentPage, int pageSize){
        int index=(CurrentPage-1)*pageSize; // 获取当前页码第一条数据的下标
        Cursor cursor=null;
        if (db!=null){
            String sql = "select * from " + tablename + " limit ?,?";
            cursor = db.rawQuery(sql,new String[]{index+"",pageSize+""});
        }
        return cursorToList(cursor);
    }

}

