package com.kelfan.tditor;

import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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
            CharSequence ss = new SpannableString(s);
            ss = addStyle(ss, s, "\\?", ColorWorker.BLUE_VERY_LIGHT);
            ss = addStyle(ss, s, "，", ColorWorker.YELLOW_VERY_LIGHT);
            ss = addStyle(ss, s, "！", ColorWorker.PURPLE_LIGHT);
            ss = addStyle(ss, s, "。", ColorWorker.YELLOW_DEEP);
            ss = addStyle(ss, s, "1", ColorWorker.BROWN_WOOD);
            ss = addStyle(ss, s, "2", ColorWorker.RED_CORAL);
            ss = addStyle(ss, s, "3", ColorWorker.YELLOW_LIGHT);
            ss = addStyle(ss, s, "4", ColorWorker.GREEN_FOREST);
            ss = addStyle(ss, s, "5", ColorWorker.BLUE_LIGHT);
            ss = addStyle(ss, s, "6", ColorWorker.BLUE_SEA);
            ss = addStyle(ss, s, "7", ColorWorker.BLUE_DEEP);
            ss = addStyle(ss, s, "8", ColorWorker.PURPLE_LIGHT);
            ss = addStyle(ss, s, "9", ColorWorker.PURPLE_DEEP);
            ss = addStyle(ss, s, " ", ColorWorker.ORANGE);
            ss = addLevel(ss, s, ",", ColorWorker.GREEN_GRASS);
            ss = addLevel(ss, s, "/", ColorWorker.GREEN_GRASS);
            ss = addBackground(ss, s, "()", ColorWorker.BLUE);
            ss = addBackground(ss, s, "<>", ColorWorker.BLUE);
            ss = addBackground(ss, s, "[]", ColorWorker.BLUE);
            ss = addBackground(ss, s, "{}", ColorWorker.BLUE);//.*\{.*\}.*
            String end = result.toString();
//            if (start.length() == end.length()) {
//                result = TextUtils.concat(result, new SpannableString(s));
//            }
//            Log.e("fan", start + " " + end);
            result = TextUtils.concat(result,ss ,new SpannableString("\n"));
        }
        return result;
    }


    public static CharSequence addStyle(CharSequence cha, String inStr, String startStr, int color) {
        String regularCondition = String.format("%s.*", startStr);
        return addCondition(cha, inStr,regularCondition, "setTextColor", color, null);
    }

    public static CharSequence addLevel(CharSequence cha, String inStr, String s1, int color) {
        String regularCondition = String.format(".*\\%s.*", s1);
        return addCondition(cha, inStr,regularCondition, "setLevel", color, s1);
    }

    public static CharSequence addBackground(CharSequence cha, String inStr, String range, int color) {
        String regularCondition = String.format(".*\\%s.*\\%s.*", range.charAt(0), range.charAt(1));
        return addCondition(cha, inStr,regularCondition, "setBackground", color, range);
    }

    public static CharSequence addCondition(CharSequence cha, String inStr, String regularCondition, String methodName, int color, String condition) {
        if (inStr.matches(regularCondition)) {
            try {
                Method m = StringStyleWorker.class.getMethod(
                        methodName,
                        String.class, int.class, String.class);
                CharSequence s = (CharSequence) m.invoke(null, inStr, color, condition);
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
        StringBuilder linesText = new StringBuilder();
        for (int z=1; z<=lines; z++) {
            linesText.append(z).append("\n");
        }
        textView.setText(linesText.toString());
    }
}
