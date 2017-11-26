package com.example.vifon.sqlitetransaction;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new MySqliteHelper(this);
    }
    /*
    点击按钮批量插入数据
     */
    public void insertData(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
        // 1. 数据库显式开启事务
        db.beginTransaction();
        for (int i=1;i<=100;i++){
            String sql="insert into "+Constant.TABLE_NAME+" values("+i+",'小慕"+i+"',18)";
            db.execSQL(sql);
        }
        // 2. 提交当前事务
        db.setTransactionSuccessful();
        // 3. 关闭事务
        db.endTransaction();
        db.close();
    }
}
