package Util;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Administrator on 14/01/2018.
 */

public class StringStyleWorker {


    public static SpannableString setTextColor(String inStr, int color) {
        SpannableString s = new SpannableString(inStr);
        s.setSpan(new ForegroundColorSpan(color),
                0, inStr.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        s.setSpan(new BackgroundColorSpan(color),0,1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        return s;
    }


    public static SpannableString backgroundYellow(String inStr) {
        SpannableString s = new SpannableString(inStr);
        s.setSpan(new BackgroundColorSpan(ColorWorker.YELLOW_DEEP), 0, inStr.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }
}
