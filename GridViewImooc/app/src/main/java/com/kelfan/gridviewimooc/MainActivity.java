package com.kelfan.gridviewimooc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private List<Map<String,Object>> datalist;
    private SimpleAdapter adapter;
    private int[] icon = {
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_assistant_black_24dp,
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_assistant_black_24dp,
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_assistant_black_24dp
    };
    private String[] iconName = {"通信录", "日历", "照相机", "通信录", "日历", "照相机", "通信录", "日历", "照相机", "通信录", "日历", "照相机"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        /**
         * 1. 准备数据源
         * 2. 新建适配器(SimpleAdapter)
         * 3. GridView加载适配器
         * 4. GridView配置事件监听器 (OnItemClickListener)
         */
        datalist = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(this,getData() ,R.layout.item, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData() {
        for (int i=0;i<icon.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            map.put("text", iconName[i]);
            datalist.add(map);
        }
        return datalist;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "我是" + iconName[position], Toast.LENGTH_SHORT).show();
    }
}
