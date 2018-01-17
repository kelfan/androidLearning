package com.kelfan.tditor;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;

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
            result = addStyle(result, s, "\\？.*", "setTextLightBlue");
            result = addStyle(result, s, "，.*", "setTextLightYellow");
            result = addStyle(result, s, "！.*", "setTextPurple");
            result = addStyle(result, s, "。.*", "setTextYellowDeep");
            result = addStyle(result, s, "4.*", "setTextGreen");
            result = addStyle(result, s, "1.*", "setTextYellow");
            result = addStyle(result, s, "2.*", "setTextRed");
            result = addStyle(result, s, "3.*", "setTextBlue");
            result = addStyle(result, s, " .*", "setTextOrange");
            String end = result.toString();
            if (start.length() == end.length()) {
                result = TextUtils.concat(result, new SpannableString(s));
            }
            Log.e("fan", start + " " + end);
            result = TextUtils.concat(result, new SpannableString("\n"));
        }
        return result;
    }

    public static CharSequence addStyle(CharSequence cha, String inStr, String regularCondition, String methodName) {
        if (inStr.matches(regularCondition)) {
            try {
                Method m = StringStyleWorker.class.getMethod(
                        methodName,
                        new Class[]{String.class}
                );
                SpannableString s = (SpannableString) m.invoke(null, inStr);
                cha = TextUtils.concat(cha, s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cha;
    }

    public static void addLineNumber(EditText editText, TextView textView, int minLines) {
        // add lines numbers in textView align with editText
        int lines = editText.getLineCount();
        if (lines <= minLines) {
            lines = minLines;
        }
        String linesText = "";
        for (int z=1; z<=lines; z++) {
            linesText = linesText + z + "\n";
        }
        textView.setText(linesText);
    }
}
