package com.kelfan.aditor.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kelfan.aditor.R;

/**
 * Created by Administrator on 11/02/2018.
 */

public class FragmentWorker {
    public static void display(Fragment fragment, int resourceId, FragmentActivity fragmentActivity) {
        if (fragment != null) {
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(resourceId, fragment);
            ft.commit();
        }
    }

    public static Fragment getFragmentLog(Fragment fragment) {
        if (fragment == null) {
            fragment = new FragmentLog();
        }
        return fragment;
    }



}
