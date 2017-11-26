package com.example.vifon.sqliteadapterdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.example.vifon.utils.Constant;

import java.io.File;

/**
 * 演示查询sdcard中数据库中的数据适配到listView中
 */

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);

        // 1. 获取数据库查询的数据
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "info.db";

        /*
        openDatabase(
            String path 表示要打开的数据库的路径,
            cursorFactory 游标工厂 可以为空,
            int flags 表示打开数据库的操作 如只读)
         */
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=db.rawQuery("select * from "+ Constant.TABLE_NAME,null);

        // 2. 讲数据加载到适配器中
        /*
        SimpleCursorAdapter(
            context context 上下文对象,
            int layout 表示 适配器控件中每项item的布局id,
            Cursor c 表示Cursor数据源,
            string[] from 表示Cursor中数据表字段的数组 显示哪些字段就写哪些,
            int[] to 表示展示字段对应值的控件资源id 表示要展示到的控件上,
            int flags 设置适配器的标记 SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER 观察者模式=有一个观察者专门观察改变 一旦发现改变就做出应对)
         */
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.list_item, cursor,
                new String[]{Constant._ID, Constant.NAME, Constant.AGE}, new int[] {R.id.tv_id, R.id.tv_name, R.id.tv_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        // 3. 将适配器的数据加载到控件
        // 使用simpleCursorAdapter, 主键名字必须是 id, 否则报异常
        lv.setAdapter(adapter);

    }
}
