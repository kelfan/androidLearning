package com.kelfan.tditor;

import android.text.Layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;

import Util.ColorWorker;
import Util.StringStyleWorker;
import Util.StringWorker;
import Util.TimeWorker;

/**
 * Created by Administrator on 14/01/2018.
 */

public class editHandler {
    public static CharSequence todoHandle(String instr) {
        ArrayList<String> strList = StringWorker.stringToList(instr, "\n");
        CharSequence result = new SpannableString("");
        for (String s : strList) {
            s = s.replace(".rq", TimeWorker.getDate());
            s = s.replace(".dt", TimeWorker.getDatetime());
            s = s.replace(".mon", TimeWorker.getNextWeekday(Calendar.MONDAY));
            s = s.replace(".tue", TimeWorker.getNextWeekday(Calendar.TUESDAY));
            s = s.replace(".wed", TimeWorker.getNextWeekday(Calendar.WEDNESDAY));
            s = s.replace(".thu", TimeWorker.getNextWeekday(Calendar.THURSDAY));
            s = s.replace(".fri", TimeWorker.getNextWeekday(Calendar.FRIDAY));
            s = s.replace(".sat", TimeWorker.getNextWeekday(Calendar.SATURDAY));
            s = s.replace(".sun", TimeWorker.getNextWeekday(Calendar.SUNDAY));
            CharSequence ss = new SpannableString(s);
            ss = addStyle(ss, "\\？", ColorWorker.BLUE_VERY_LIGHT);
            ss = addStyle(ss, "，", ColorWorker.YELLOW_VERY_LIGHT);
            ss = addStyle(ss, "！", ColorWorker.PURPLE_LIGHT);
            ss = addStyle(ss, "。", ColorWorker.YELLOW_DEEP);
            ss = addStyle(ss, "1", ColorWorker.BROWN_WOOD);
            ss = addStyle(ss, "2", ColorWorker.RED_CORAL);
            ss = addStyle(ss, "3", ColorWorker.YELLOW_LIGHT);
            ss = addStyle(ss, "4", ColorWorker.GREEN_FOREST);
            ss = addStyle(ss, "5", ColorWorker.BLUE_LIGHT);
            ss = addStyle(ss, "6", ColorWorker.BLUE_SEA);
            ss = addStyle(ss, "7", ColorWorker.BLUE_DEEP);
            ss = addStyle(ss, "8", ColorWorker.PURPLE_LIGHT);
            ss = addStyle(ss, "9", ColorWorker.PURPLE_DEEP);
            ss = addStyle(ss, " ", ColorWorker.ORANGE);
            ss = addLevel(ss, ",", ColorWorker.GREEN_GRASS);
            ss = addLevel(ss, "/", ColorWorker.GREEN_GRASS);
            ss = addBackground(ss, "()", ColorWorker.BLUE);
            ss = addBackground(ss, "<>", ColorWorker.BLUE);
            ss = addBackground(ss, "[]", ColorWorker.BLUE);
            ss = addBackground(ss, "{}", ColorWorker.BLUE);
            result = TextUtils.concat(result, ss, new SpannableString("\n"));
        }
        return result;
    }


    public static CharSequence addStyle(CharSequence cha, String startStr, int color) {
        String regularCondition = String.format("%s.*", startStr);
        return addCondition(cha, regularCondition, "setTextColor", color, null);
    }

    public static CharSequence addLevel(CharSequence cha, String s1, int color) {
        String regularCondition = String.format(".*\\%s.*", s1);
        return addCondition(cha, regularCondition, "setLevel", color, s1);
    }

    public static CharSequence addBackground(CharSequence cha, String range, int color) {
        String regularCondition = String.format(".*\\%s.*\\%s.*", range.charAt(0), range.charAt(1));//.*\{.*\}.*
        return addCondition(cha, regularCondition, "setBackground", color, range);
    }

    public static CharSequence addCondition(CharSequence cha, String regularCondition, String methodName, int color, String condition) {
        if (cha.toString().matches(regularCondition)) {
            try {
                Method m = StringStyleWorker.class.getMethod(
                        methodName,
                        String.class, int.class, String.class);
                CharSequence s = (CharSequence) m.invoke(null, cha.toString(), color, condition);
                cha = s;
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
        Layout layout = editText.getLayout();
        StringBuilder linesText = new StringBuilder();
        int count = 1;
        for (int z = 1; z <= lines; z++) {
            if (layout != null && z > 1) {
                int curIndex = layout.getLineEnd(z-2);
                char end = editText.getText().charAt(curIndex-1);
                if (end != '\n') {
                    linesText.append("*").append("\n");
                } else {
                    linesText.append(count).append("\n");
                    count++;
                }
            } else {
                linesText.append(count).append("\n");
                count++;
            }
        }
        textView.setText(linesText.toString());
    }
}
