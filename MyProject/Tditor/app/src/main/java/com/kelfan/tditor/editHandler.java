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
            result = addStyle(result, s, "\\?", ColorWorker.BLUE_VERY_LIGHT);
            result = addStyle(result, s, "，", ColorWorker.YELLOW_VERY_LIGHT);
            result = addStyle(result, s, "！", ColorWorker.PURPLE_LIGHT);
            result = addStyle(result, s, "。", ColorWorker.YELLOW_DEEP);
            result = addStyle(result, s, "1", ColorWorker.BROWN_WOOD);
            result = addStyle(result, s, "2", ColorWorker.RED_CORAL);
            result = addStyle(result, s, "3", ColorWorker.YELLOW_LIGHT);
            result = addStyle(result, s, "4", ColorWorker.GREEN_FOREST);
            result = addStyle(result, s, "5", ColorWorker.BLUE_LIGHT);
            result = addStyle(result, s, "6", ColorWorker.BLUE_SEA);
            result = addStyle(result, s, "7", ColorWorker.BLUE_DEEP);
            result = addStyle(result, s, "8", ColorWorker.PURPLE_LIGHT);
            result = addStyle(result, s, "9", ColorWorker.PURPLE_DEEP);
            result = addStyle(result, s, " ", ColorWorker.ORANGE);
            result = addLevel(result, s, ",", ColorWorker.GREEN_GRASS);
            result = addLevel(result, s, "/", ColorWorker.GREEN_GRASS);
            result = addBackground(result, s, "()", ColorWorker.BLUE);
            result = addBackground(result, s, "<>", ColorWorker.BLUE);
            result = addBackground(result, s, "[]", ColorWorker.BLUE);
            result = addBackground(result, s, "{}", ColorWorker.BLUE);//.*\{.*\}.*
            String end = result.toString();
            if (start.length() == end.length()) {
                result = TextUtils.concat(result, new SpannableString(s));
            }
            Log.e("fan", start + " " + end);
            result = TextUtils.concat(result, new SpannableString("\n"));
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
        StringBuilder linesText = new StringBuilder();
        for (int z=1; z<=lines; z++) {
            linesText.append(z).append("\n");
        }
        textView.setText(linesText.toString());
    }
}
