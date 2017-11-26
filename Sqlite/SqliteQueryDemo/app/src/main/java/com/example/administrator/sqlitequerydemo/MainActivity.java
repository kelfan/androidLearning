package com.example.administrator.sqlitequerydemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.bean.Person;
import com.example.administrator.utils.Constant;
import com.example.administrator.utils.DbManager;
import com.example.administrator.utils.MySqliteHelper;

import java.util.List;

/**
 * 演示Sqlite数据库中的查询操作
 */
public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= DbManager.getInstance(this);
    }

    /*
    点击按钮 创建数据库并且插入测试数据
     */
    public void createDb(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        for(int i=1;i<=30;i++){
            String sql ="insert into "+ Constant.TABLE_NAME+" values("+i+",'张山"+i+"',20)";
            db.execSQL(sql);
        }
        db.close();
    }

    /*
    点击按钮查询表中数据
     */
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_query:
                SQLiteDatabase db=helper.getWritableDatabase();
                String selectSql="select * from "+Constant.TABLE_NAME;
                Cursor cursor=DbManager.selectDataBySql(db,selectSql,null);
                List<Person> list=DbManager.cursorToList(cursor);
                for (Person p:list){
                    Log.i("tag",p.toString());
                }
                db.close();
                break;
            case R.id.btn_queryApi:
                db=helper.getWritableDatabase();
                // select _id,name,age  from person where _id>10 group by x having x order by x
                /*
                query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
                    String table = 要查询的表
                    String[] = 查询表中的字段; null=查询所有
                    String selection 表示查询条件 where 语句
                    String[] selectionArgs 表示查询占位符的取值
                    String group by 表示分组条件 group by子句
                    String having 表示筛选条件 having子句
                    String orderBy 表示排序条件 order by 子句
                 */
                cursor=db.query(Constant.TABLE_NAME,null,Constant._ID+"=?",new String[]{"10"},null,null,Constant._ID+" desc");
                list=DbManager.cursorToList(cursor);
                for (Person p:list){
                    Log.i("tag",p.toString());
                }
                db.close();
                break;
        }
    }
}
