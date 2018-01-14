package com.kelfan.ceditor;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Administrator on 13/01/2018.
 */

public class ColorEditText extends android.support.v7.widget.AppCompatEditText {
    private void initialize() {
        this.addTextChangedListener(new ColorTextWatcher());
    }

    public ColorEditText(Context context) {
        super(context);
        initialize();
    }

    public ColorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ColorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }


}
