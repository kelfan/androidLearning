package com.example.vifon.sqliteadapterdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vifon.utils.Constant;

import java.io.File;

/**
 * Created by vifon on 17/11/19.
 */

public class CursorAdapterActivity extends AppCompatActivity {
    private ListView lv;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle saveInsanceState){
        super.onCreate(saveInsanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "info.db";
        db = SQLiteDatabase.openDatabase(path, null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from "+ Constant.TABLE_NAME,null);
        MyCursorAdapter adapter = new MyCursorAdapter(this,cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adapter);
    }

    // cursorAdpater 是抽象类,必须继承才可以实例化

    /**
     * 以内部类形式定义适配器
     */
    public class MyCursorAdapter extends CursorAdapter{
        // 必须定义构造方法
        public MyCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        /**
         * 表示创建适配器控件中每个item对应的view对象
         * @param context 上下文
         * @param cursor 数据源cursor对象
         * @param viewGroup 当前item的父布局
         * @return 每项item的view对象
         */
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view = LayoutInflater.from(CursorAdapterActivity.this).inflate(R.layout.list_item,null);
            return view;
        }

        /**
         * 通过newView() 方法确定了每个item展示的view对象 在bindview()中对布局中的控件进行填充
         * @param view 由newView() 返回每项view对象
         * @param context 上下文
         * @param cursor 数据源cursor对象
         */
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_age = (TextView) view.findViewById(R.id.tv_age);

            // setText 如果参数是数字就是当成资源id去找资源而不是输出,所以最后+"",转为字符串
            tv_id.setText(cursor.getInt(cursor.getColumnIndex(Constant._ID)) + "");
            tv_name.setText(cursor.getString(cursor.getColumnIndex(Constant.NAME)));
            tv_age.setText(cursor.getInt(cursor.getColumnIndex(Constant.AGE))+"");
        }


    }
}
