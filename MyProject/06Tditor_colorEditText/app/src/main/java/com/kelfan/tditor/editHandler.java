package com.kelfan.tditor;

import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

import Util.ColorWorker;
import Util.FileHandler;
import Util.StringStyleWorker;
import Util.StringWorker;
import Util.TimeWorker;

/**
 * Created by Administrator on 14/01/2018.
 */

public class editHandler {
    public static String replaceActions(String s) {
        // all string command codes can change to Constant files for change or setting
        s = s.replace(".rq", TimeWorker.getDate()); // get Current time
        s = s.replace(".dt", TimeWorker.getDatetime()); // get Current datetime
        s = s.replace(".mon", TimeWorker.getNextWeekday(Calendar.MONDAY));
        s = s.replace(".tue", TimeWorker.getNextWeekday(Calendar.TUESDAY));
        s = s.replace(".wed", TimeWorker.getNextWeekday(Calendar.WEDNESDAY));
        s = s.replace(".thu", TimeWorker.getNextWeekday(Calendar.THURSDAY));
        s = s.replace(".fri", TimeWorker.getNextWeekday(Calendar.FRIDAY));
        s = s.replace(".sat", TimeWorker.getNextWeekday(Calendar.SATURDAY));
        s = s.replace(".sun", TimeWorker.getNextWeekday(Calendar.SUNDAY));
        s = s.replace(".nmon", TimeWorker.getNextWeekday(7 + Calendar.MONDAY ));
        s = s.replace(".ntue", TimeWorker.getNextWeekday(7 + Calendar.TUESDAY));
        s = s.replace(".nwed", TimeWorker.getNextWeekday(7 + Calendar.WEDNESDAY));
        s = s.replace(".nthu", TimeWorker.getNextWeekday(7 + Calendar.THURSDAY));
        s = s.replace(".nfri", TimeWorker.getNextWeekday(7 + Calendar.FRIDAY));
        s = s.replace(".nsat", TimeWorker.getNextWeekday(7 + Calendar.SATURDAY));
        s = s.replace(".nsun", TimeWorker.getNextWeekday(7 + Calendar.SUNDAY));
        s = s.replace(".nnmon", TimeWorker.getNextWeekday(14 + Calendar.MONDAY ));
        s = s.replace(".nntue", TimeWorker.getNextWeekday(14 + Calendar.TUESDAY));
        s = s.replace(".nnwed", TimeWorker.getNextWeekday(14 + Calendar.WEDNESDAY));
        s = s.replace(".nnthu", TimeWorker.getNextWeekday(14 + Calendar.THURSDAY));
        s = s.replace(".nnfri", TimeWorker.getNextWeekday(14 + Calendar.FRIDAY));
        s = s.replace(".nnsat", TimeWorker.getNextWeekday(14 + Calendar.SATURDAY));
        s = s.replace(".nnsun", TimeWorker.getNextWeekday(14 + Calendar.SUNDAY));
        s = s.replace(".dqt", TimeWorker.getNextDay(-3)); //大前天
        s = s.replace(".qt", TimeWorker.getNextDay(-2)); //前天
        s = s.replace(".zt", TimeWorker.getNextDay(-1)); // 昨天
        s = s.replace(".jt", TimeWorker.getNextDay(0)); // 今天
        s = s.replace(".mt", TimeWorker.getNextDay(1)); //明天
        s = s.replace(".ht", TimeWorker.getNextDay(2)); //后天
        s = s.replace(".dht", TimeWorker.getNextDay(3)); //大后天
        s = s.replace(".nsz", TimeWorker.getNextDay(28)); // 下四周

        // quick actions to symbols
        if (s.contains("，，")) { s = "，   ,"+TimeWorker.getDatetime()+"," + s.replace("，，", ""); }
        if (s.contains("。。")) { s = "0" + s.replace("。。", "").substring(1); }
        if (s.contains("？？")) { s = "？ ,"+TimeWorker.getDatetime()+"," + s.replace("？？", ""); }
        if (s.contains("！！")) { s = "！ " + s.replace("！！", ""); }
//            if (s.contains("//1")) { s = "1 " + s.replace("//1", ""); }
//            if (s.contains("//2")) { s = "2 " + s.replace("//2", ""); }
//            if (s.contains("//3")) { s = "3 " + s.replace("//3", ""); }
//            if (s.contains("//4")) { s = "4 " + s.replace("//4", ""); }
//            if (s.contains("//5")) { s = "5 " + s.replace("//5", ""); }
//            if (s.contains("//6")) { s = "6 " + s.replace("//6", ""); }
//            if (s.contains("//7")) { s = "7 " + s.replace("//7", ""); }
//            if (s.contains("//8")) { s = "8 " + s.replace("//8", ""); }
//            if (s.contains("//9")) { s = "9 " + s.replace("//9", ""); }
        return s;
    }


    public static CharSequence addTextStyle(String instr) {
        ArrayList<String> strList = StringWorker.stringToList(instr, "\n");
        CharSequence result = new SpannableString("");
        for (String s : strList) {
            CharSequence ss = new SpannableString(s);

            // change color based on start symbol
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
            ss = addStyle(ss, "0", ColorWorker.ORANGE);
//            ss = addLevel(ss, ",", ColorWorker.GREEN_GRASS);

            // add backgrounds to lines
            ss = addLevel(ss, "/", ColorWorker.GREEN_GRASS);
            ss = addBackground(ss, "()", ColorWorker.BLUE);
            ss = addBackground(ss, "<>", ColorWorker.BLUE);
            ss = addBackground(ss, "[]", ColorWorker.BLUE);
            ss = addBackground(ss, "{}", ColorWorker.BLUE);
            ss = addStyle(ss, "\\？", ColorWorker.BLUE_VERY_LIGHT);
            ss = addStyle(ss, "，", ColorWorker.YELLOW_VERY_LIGHT);
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
        return addCondition(cha, regularCondition, "addLevel", color, s1);
    }

    public static CharSequence addBackground(CharSequence cha, String range, int color) {
        String regularCondition = String.format(".*\\%s.*\\%s.*", range.charAt(0), range.charAt(1));//.*\{.*\}.*
        return addCondition(cha, regularCondition, "addBackground", color, range);
    }

    public static CharSequence addCondition(CharSequence cha, String regularCondition, String methodName, int color, String condition) {
        if (cha.toString().matches(regularCondition)) {
            try {
                Method m = StringStyleWorker.class.getMethod(
                        methodName,
                        CharSequence.class, int.class, String.class);
                CharSequence s = (CharSequence) m.invoke(null, cha, color, condition);
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
                int curIndex = layout.getLineEnd(z - 2);
                char end = editText.getText().charAt(curIndex - 1);
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

    public static void setCursor(EditText editText) {
        int cursorPosition = editText.getSelectionStart();
        editText.setSelection(cursorPosition);
    }


    public static void saveFile(EditText editText, String path, View view) {
        String txt = editText.getText().toString();
        String[] txtList = txt.split("，     ");
        String edTxt = txtList[0]+ "，     ";
        int result = FileHandler.write_app_file(path, edTxt);
        int log = Constant.RESULT_SUCCESS;
        if (!txtList[1].equals("\n")) {
            log = FileHandler.append_app_file(Constant.DEFAULT_LOG_NAME, txtList[1]);
        }
        CharSequence cs = addTextStyle(edTxt);
        int cursorPosition = editText.getSelectionStart();
        editText.setText(cs);
        int len = cs.length();
        if (len < cursorPosition) {
            cursorPosition = len;
        }
        editText.setSelection(cursorPosition);
        if (result == Constant.RESULT_SUCCESS && log == Constant.RESULT_SUCCESS) {
            Snackbar.make(view, "Save success", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(view, "Fail to save file", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
