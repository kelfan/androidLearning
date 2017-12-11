package com.kelfan.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;

/**
 * Created by Administrator on 11/12/2017.
 */

public class NumberReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            dbHelper.saveNumber(number, sqLiteDatabase);
            dbHelper.close();
        }

        Intent intent1 = new Intent(DbContract.UPDATE_UI_FILTER);
        context.sendBroadcast(intent1);
    }
}
