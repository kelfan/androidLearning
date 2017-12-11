# Android BroadcastReceiver Example - codes 
[YouTube](https://www.youtube.com/watch?v=hD0rybdMtGk)
This is example to records the phone call numbers of the phone through BroadcastReceiver.

# BroadcastReceiver
1. class extends `BroadcastReceiver`
1. `AndroidMainfest` add receiver

# broadcastReceiver class 
```java 
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
    }
}
```

# AndroidMainfest add receiver
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.kelfan.broadcastreceiverexample">
    <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <receiver android:name=".NumberReceiver">
            <intent-filter>
                <action android:name="android.intent.action.PHONE_STATE"></action>
            </intent-filter>
        </receiver>
    </application>

</manifest>
```