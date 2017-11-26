package com.kelfan.activitydemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/26.
 */

public class firstActivity extends Activity {
    private Button btn1, btn2;
    private Context mContext;
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);
//        1. Button To another Page
        mContext = this;
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        tv = findViewById(R.id.tv1);
//        2. onClick Event
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** parameter
                 * 1. Context
                 * 2. target Activity
                 */
                Intent intent = new Intent(mContext,secondActivity.class); //或者使用 firstActivity.this 代替使用 mContext 变量
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, secondActivity.class);
                /** parameter
                 * 1. Intent Object
                 * 2. Symbol Of request, identify request From which Activity
                 */
                startActivityForResult(intent,1);
            }
        });
    }

    /**
     *  To receive the Result From another Activity
     * @param requestCode Symbol Of request
     * @param resultCode the Symbol Return From another Activity, From identify From which Activity
     * @param data the Return Data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            String content = data.getStringExtra("data");
            tv.setText(content);
        }
    }
}
