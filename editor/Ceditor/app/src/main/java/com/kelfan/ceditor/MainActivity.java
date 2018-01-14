package com.kelfan.ceditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ColorEditText colorEditText;

    private int selectionStart;
    private int selectionEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorEditText = findViewById(R.id.colorEditText);
//        colorEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                Log.i("fan", "focus changed");
//            }
//        });
    }
}
