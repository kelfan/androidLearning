package com.kelfan.activitydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"Mainactivity --> onCreate");
        Button btn = findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        Second_Activity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"Mainactivity --> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Mainactivity --> onResume");
}

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Mainactivity --> onPause");
}

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"Mainactivity --> onRestart");
}

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Mainactivity --> onStop");
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Mainactivity --> onDestroy");
}

}
