package com.kelfan.recyclerviewimooc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> mData;
    private SimpleAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        mAdapter = new SimpleAdapter(this, mData);
        recyclerView.setAdapter(mAdapter);

        // 设置recyclerView布局管理
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i='A'; i<='z'; i++) {
            mData.add("" + (char) i);
        }

    }
}
