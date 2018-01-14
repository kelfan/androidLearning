package com.kelfan.ceditor;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ColorEditText colorEditText;
    private EditText editText;

    private int selectionStart;
    private int selectionEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorEditText = findViewById(R.id.colorEditText);
        editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new ColorTextWatcher());
        String s = "hello world";
        String s2 = "这不是事";
        SpannableString ss = new SpannableString(s);
        SpannableString ss2 = new SpannableString(s2);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(34,141,65)),
                0, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        ss2.setSpan(new BackgroundColorSpan(Color.GREEN), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setText(TextUtils.concat(ss, "\n", ss2));

        String s3 = "<h1>hello world</h1>";
        String s4 = "This is <font color='red'>simple</font>.";
        String s5 = "<div style=\"color:#0000FF\">\n" +
                "  <h3>This is a heading</h3>\n" +
                "  <p>This is a paragraph.</p>\n" +
                "</div>";
        colorEditText.setText(Html.fromHtml(s5));
//        colorEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                Log.i("fan", "focus changed");
//            }
//        });
    }
}
