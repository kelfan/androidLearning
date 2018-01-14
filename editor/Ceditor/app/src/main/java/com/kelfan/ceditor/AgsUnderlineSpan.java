package com.kelfan.ceditor;

import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

/**
 * Created by Administrator on 13/01/2018.
 * 设定文本下拉线的类
 */

class AgsUnderlineSpan extends CharacterStyle implements UpdateAppearance, ParcelableSpan {
    public AgsUnderlineSpan() {
    }

    public AgsUnderlineSpan(Parcel src) {
    }

    @Override
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(true);
    }

    @Override
    public int getSpanTypeId() {
        return 6;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

    }
}
