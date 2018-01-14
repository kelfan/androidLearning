package com.kelfan.ceditor;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;

import java.io.Console;

/**
 * Created by Administrator on 13/01/2018.
 */

public class ColorTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        Log.i("fan", String.valueOf(editable));
        editable.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); // 不包括开头加入的,但包括结尾插入
        editable.setSpan(new UnderlineSpan(), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE); // 开头结尾插入都包括
        editable.setSpan(new StyleSpan(Typeface.BOLD),0,1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        editable.setSpan(new BackgroundColorSpan(Color.GREEN), 0, 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        TypefaceSpan typefaceSpan = new TypefaceSpan("sans-serif-thin");
        editable.setSpan(typefaceSpan,0,1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        //      italic is not working in editText
//  editable.setSpan(new StyleSpan(Typeface.ITALIC), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
    }
}
