package com.example.vifon.sqlitedivpage;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.vifon.adapter.MyBaseAdapter;
import com.example.vifon.bean.Person;
import com.example.vifon.utils.Constant;
import com.example.vifon.utils.DbManager;

import java.io.File;
import java.util.List;

/**
 * 演示数据库分页
 *
 * select * from Person limit 0,15; 当前页码当先下标,每页显示的条目
 *
 * 总条目  201
 * 每页条数 20
 * 总页数
 * 页码
 *
 * 1. 加载第一页
 * 2. 加载下一页
 */

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private SQLiteDatabase db;
    private int totalNum; // 表示当前控件加载数据的总条目
    private int pageSize=20; // 表示每页展示数据的条目
    private int pageNum; // 表示总页数
    private int currentPage=1; //当前页码
    private List<Person> totalList; //表示数据源
    private MyBaseAdapter adapter;
    private Boolean isDivPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv);

        String path= Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator+"info.db";
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        // 获取数据总条目
        totalNum = DbManager.getDataCount(db, Constant.TABLE_NAME);
        // 根据总条目与每页展示数据条目 获得总页数
        pageNum= (int) Math.ceil(totalNum/(double)pageSize);
        if (currentPage == 1) {
            totalList = DbManager.getListByCurrentPage(db, Constant.TABLE_NAME, currentPage, pageSize);
        }
        adapter=new MyBaseAdapter(this,totalList);
        lv.setAdapter(adapter);

        lv.setOnScrollListener(new AbsListView.OnScrollListener(){

            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // AbsListView.OnScrollListener.SCROLL_STATE_IDLE 表示滚动条停止时的状态
                if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
                    if (currentPage < pageNum) {
                        currentPage++;
                        // 根据最新的页码加载获取集合存储到数据源中
                        totalList.addAll(DbManager.getListByCurrentPage(db,Constant.TABLE_NAME,
                                currentPage,pageSize));
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                isDivPage=((firstVisibleItem+visibleItemCount)==totalItemCount);
            }
        });
    }
}
