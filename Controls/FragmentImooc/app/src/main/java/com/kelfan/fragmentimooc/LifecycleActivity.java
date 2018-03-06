package com.kelfan.fragmentimooc;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by jzheng4 on 6/03/2018.
 */

// LifeCycle 演示：启动Fragment--> 屏幕锁屏--> 屏幕解锁--> 切换到其他Fragment--> 回到桌面--> 回到应用--> 退出Fragment
    // 观察 log

public class LifecycleActivity extends Activity {

    private Button button;
    private Fragment fragment;
    private boolean flag = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        init();
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if (flag) {
                    LifecycleFragment2 lifecycleFragment2 = new LifecycleFragment2();
                    fragmentTransaction.replace(R.id.layout, lifecycleFragment2);
                    flag = false;
                } else {
                    LifecycleFragment lifecycleFragment = new LifecycleFragment();
                    fragmentTransaction.replace(R.id.layout, lifecycleFragment);
                    flag = true;
                }

                fragmentTransaction.commit();
            }
        });
    }

    private void init() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LifecycleFragment lifecycleFragment = new LifecycleFragment();
        fragmentTransaction.add(R.id.layout, lifecycleFragment);
    }

}
