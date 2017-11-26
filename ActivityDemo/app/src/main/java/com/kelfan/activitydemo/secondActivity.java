package com.kelfan.activitydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/11/26.
 */

public class secondActivity extends Activity{
    private Button btn1;
    private String returnTxt = "hello world";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        /*
        Return Data To first Activity
        the Result actually is an Intent back To the first Activity
         */
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("data", returnTxt);
                setResult(2,intent);
                // end this Page
                finish();
            }
        });
    }
}
