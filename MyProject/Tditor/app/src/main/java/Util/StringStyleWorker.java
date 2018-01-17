package Util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Administrator on 14/01/2018.
 */

public class StringStyleWorker {


    public static SpannableString setTextColor(String inStr, int color, String s2) {
        SpannableString s = new SpannableString(inStr);
        s.setSpan(new ForegroundColorSpan(color),
                0, inStr.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        s.setSpan(new BackgroundColorSpan(color),0,1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return s;
    }

    public static SpannableString setBackground(String inStr, int color, String range) {
        SpannableString s = new SpannableString(inStr);
        int pos1 = inStr.toLowerCase().indexOf(range.toLowerCase().charAt(0));
        int pos2 = inStr.toLowerCase().indexOf(range.toLowerCase().charAt(1)) + 1;
        s.setSpan(new ForegroundColorSpan(ColorWorker.getComplimentColor(color)),
                pos1, pos2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        s.setSpan(new BackgroundColorSpan(color),pos1,pos2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return s;
    }

    public static CharSequence setLevel(String inStr, int color, final String delimiter) {
        String[] sList = inStr.split(delimiter);
        CharSequence result = new SpannableString("");
        for (int i=0; i<sList.length-1;i++) {
            SpannableString s = new SpannableString(sList[i]+delimiter);
            s.setSpan(new ForegroundColorSpan(ColorWorker.getComplimentColor(color)),
                    0, sList[i].length()+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            s.setSpan(new BackgroundColorSpan(color),0,sList[i].length()+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            color = ColorWorker.getComplimentColor(color);
            result = TextUtils.concat(result, s);
        }
        result = TextUtils.concat(result, sList[sList.length-1]);
        return result;
    }

    public static SpannableString backgroundYellow(String inStr) {
        SpannableString s = new SpannableString(inStr);
        s.setSpan(new BackgroundColorSpan(ColorWorker.YELLOW_DEEP), 0, inStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }
}
