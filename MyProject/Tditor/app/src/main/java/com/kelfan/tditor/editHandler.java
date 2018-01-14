package com.kelfan.tditor;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

import Util.ColorWorker;
import Util.StringStyleWorker;
import Util.StringWorker;

/**
 * Created by Administrator on 14/01/2018.
 */

public class editHandler {
    public static CharSequence todoHandle(String instr) {
        ArrayList<String> strList = StringWorker.stringToList(instr, "\n");
        CharSequence result = new SpannableString("");
        for (String s : strList) {
            String start = result.toString();
            result = addStyle(result, s, "\\？.*", ColorWorker.BLUE_VERY_LIGHT,  "setTextColor");
            result = addStyle(result, s, "，.*", ColorWorker.YELLOW_VERY_LIGHT,"setTextColor");
            result = addStyle(result, s, "！.*", ColorWorker.PURPLE_LIGHT,"setTextColor");
            result = addStyle(result, s, "。.*", ColorWorker.YELLOW_DEEP,"setTextColor");
            result = addStyle(result, s, "4.*", ColorWorker.GREEN_SEA,"setTextColor");
            result = addStyle(result, s, "1.*", ColorWorker.BROWN_WOOD,"setTextColor");
            result = addStyle(result, s, "2.*", ColorWorker.RED_CORAL,"setTextColor");
            result = addStyle(result, s, "3.*", ColorWorker.BLUE_SEA,"setTextColor");
            result = addStyle(result, s, " .*", ColorWorker.ORANGE,"setTextColor");
            String end = result.toString();
            if (start.length() == end.length()) {
                result = TextUtils.concat(result, new SpannableString(s));
            }
            Log.e("fan", start + " " + end);
            result = TextUtils.concat(result, new SpannableString("\n"));
        }
        return result;
    }

    public static CharSequence addStyle(CharSequence cha, String inStr, String regularCondition, int color,  String methodName) {
        if (inStr.matches(regularCondition)) {
            try {
                Method m = StringStyleWorker.class.getMethod(
                        methodName,
                        new Class[]{String.class, int.class}
                );
                SpannableString s = (SpannableString) m.invoke(null, inStr, color);
                cha = TextUtils.concat(cha, s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cha;
    }
}
