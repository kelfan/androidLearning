package com.kelfan.aditor.Util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 1/01/2018.
 */

public class NotificationWorker {
    public static void notifyResult(Context context, String action,int result) {
        if (result == 1) {
            Toast.makeText(context, action + " success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, action + " fail", Toast.LENGTH_SHORT).show();
        }
    }
    public static void notifyResult(View view, String action, int result) {
        if (result == 1) {
            Snackbar.make(view, "success to " + action, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(view, "fail to " + action, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }


}
