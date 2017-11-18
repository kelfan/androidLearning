package com.example.administrator.sqlitedemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.utils.Constant;
import com.example.administrator.utils.DbManager;
import com.example.administrator.utils.MySqliteHelper;

public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper= DbManager.getInstance(this);
    }

    /*
    * 点击按钮创建数据库
    * */
    public void createDb(View view){
        /*
        getReadableDatabase() getWritableDatabase() 都是创建或打开数据库
        默认情况下两个都是可读可写
        特殊情况如磁盘已满或数据库本身权限等情况getReadableDatabase()是只读
         */
        SQLiteDatabase db = helper.getWritableDatabase();

    }

    // 使用Sql语句执行数据库操作
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_insert:
                SQLiteDatabase db = helper.getWritableDatabase();
                String sql = "insert into "+ Constant.TABLE_NAME +" values(1, 'zhangsan', 20)";
                DbManager.execSQL(db,sql);
                String sql2 = "insert into "+ Constant.TABLE_NAME +" values(2,'lisi',25)";
                db.close();
                break;
            case R.id.btn_update:
                db=helper.getWritableDatabase();
                String updateSql="update"+Constant.TABLE_NAME
                        +" set "+Constant.NAME+"='xiaoming' where "+Constant._ID+"=1";
                DbManager.execSQL(db,updateSql);
                db.close();
                break;
            case R.id.btn_delete:
                db=helper.getWritableDatabase();
                String deleteSql="delete from "+Constant.TABLE_NAME+" where "+Constant._ID+"=2";
                db.execSQL(deleteSql);
                db.close();
                break;
        }
    }

    // 使用API执行数据库操作
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_insertApi:
                SQLiteDatabase db=helper.getWritableDatabase();
                /**
                 * insert(String table, String nullcolumnhack, Contentvalues values)
                 * String table 表示插入数据表的名称
                 * String nullColumnHack 不允许一列全部为空
                 * ContentValues values 键为String类型的hashmap集合 一般有多少字段就要放入多少put在ConstantValues上
                 * 返回值 long类型 表示插入数据的列数
                 */
                ContentValues values = new ContentValues();
                values.put(Constant._ID, 3); // put(表示插入数据库的字段,表示插入该字段的具体值)
                values.put(Constant.NAME, "张山");
                values.put(Constant.AGE,30);
                long result = db.insert(Constant.TABLE_NAME,null,values);
                if (result>0){
                    Toast.makeText(MainActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "插入数据失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.btn_updateApi:
                db=helper.getWritableDatabase();
                /**
                 * update(String table, ContentValues values, String whereClause, String[] whereArgs);
                 * String table 表示修改的数据表的名称
                 * Constantvalues 表示键为String类型的hashmap
                 * String whereClause 表示修改条件
                 * String[] whereArgs 表示修改条件的占位符,实际上就是实际条件的字符串集合
                 * 返回值表示修改的条数
                 */
                ContentValues cv = new  ContentValues();
                cv.put(Constant.NAME, "小慕"); // put(需要修改的字段名称, 修改后的字段值)
//                int count = db.update(Constant.TABLE_NAME, cv, Constant._ID+"=3",null); // 这是直接把查询条件放前面 _id=3
                int count = db.update(Constant.TABLE_NAME, cv, Constant._ID+"=?",new String[]{"3"}); // 这是使用占位符? 然后用最后的参数代替
                if (count>0){
                    Toast.makeText(MainActivity.this, "修改数据成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "修改数据失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
            case R.id.btn_delete:
                db=helper.getWritableDatabase();
                /**
                 * delete(String table, String whereClause, String[] whereArgs)
                 * String table, 表示要删除数据的数据表
                 * String whereClause, 表示删除的条件
                 * String[] whereArgs 表示删除条件的占位符,就是条件的可能取值
                 */
                int count2 = db.delete(Constant.TABLE_NAME, Constant._ID+"=?",new String[]{"1"});
                if (count2>0){
                    Toast.makeText(MainActivity.this, "删除数据成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "删除数据失败", Toast.LENGTH_SHORT).show();
                }
                db.close();
                break;
        }

    }
}
