package com.kelfan.fortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kelfan.fortest.Util.ColorWorker;
import com.kelfan.fortest.Util.StringStyleWorker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv);
        String s = "hello/aere/jllj";
        CharSequence cs = StringStyleWorker.setTextColor(s, ColorWorker.BLUE, "jllj");
        cs = StringStyleWorker.addLevel(cs, ColorWorker.GREEN_COPPER, "/");
        textView.setText(cs);
    }
}
