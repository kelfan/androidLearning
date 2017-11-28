package com.kelfan.listviewdemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {

    private ListView listView;
    private ListView simpleListView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. acquire ListView
        listView = findViewById(R.id.list_view);
        simpleListView = findViewById(R.id.simple_list_view);
        // 2. acquire Datasource
        String[] arrayData = {"Apple", "banana", "cat", "dog"};
        datalist = new ArrayList<Map<String, Object>>();
        // 3. create Adapter
        /**
         * parameter
         * @context this
         * @LayoutResources xml View To display data
         * @Resources data source
         */
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayData);
        /**
         * parameters
         * @context
         * @data datasource a key-value map
         * @resource Layout
         * @map key in the map
         * @to id in the View
         */
        simpleAdapter = new SimpleAdapter(this,
                getData(),
                R.layout.item,
                new String[]{"pic","text"},
                new int[]{R.id.pic, R.id.text});
        // 4. attach datasource To View
        listView.setAdapter(arrayAdapter);
        simpleListView.setAdapter(simpleAdapter);
        simpleListView.setOnItemClickListener(this);
        simpleListView.setOnScrollListener(this);
    }

    private List<Map<String, Object>> getData() {
        for(int i=0;i<20;i++) {
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("pic", R.mipmap.ic_launcher_round);
            map.put("text", "thing" + i);
            datalist.add(map);
        }
        return datalist;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        switch (scrollState) {
            case SCROLL_STATE_FLING:
                Log.i("Main", "用户在手指离开屏幕后,屏幕惯性滑动");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("pic", R.mipmap.ic_launcher_round);
                map.put("text", "add item");
                datalist.add(map);
                simpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main", "停止滚动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main", "触屏滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String text = simpleListView.getItemAtPosition(position) + "";
        Toast.makeText(this,"position"+position+" text="+text, Toast.LENGTH_SHORT).show();
    }
}
