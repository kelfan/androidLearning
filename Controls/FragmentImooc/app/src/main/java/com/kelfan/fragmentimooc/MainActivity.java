package com.kelfan.fragmentimooc;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_static:
                    mTextMessage.setText("静态加载");
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_dynamic:
                    mTextMessage.setText("动态加载");
                    DynamicFragment dynamicFragment = new DynamicFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // 第一个参数是 装fragment的元素Id, 第二个参数是 fragment对象
                    fragmentTransaction.add(R.id.frame, dynamicFragment);
                    // 可以让fragment可以返回到上一个
                    fragmentTransaction.addToBackStack(null);
                    // 添加完后就是提交
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_lifecycle:
                    mTextMessage.setText("LifeCycle");
                    intent = new Intent(MainActivity.this, LifecycleActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_communicate:
                    mTextMessage.setText("通信");
                    intent = new Intent(MainActivity.this, CommuteActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
