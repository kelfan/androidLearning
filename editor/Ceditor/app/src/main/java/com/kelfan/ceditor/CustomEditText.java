package com.kelfan.ceditor;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ToggleButton;

/**
 * Created by Administrator on 13/01/2018.
 * 1. constructor 构造函数
 * 2. Eventback 回调函数
 * 3. onKeyPreIme
 * 4. initialize()
 * 5. Textwatcher
 * 6. aftertextchange 对各种样式的处理
 */

public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    // Log tag
    public static final String TAG = "DriodWriter";

    // Style constants
    private static final int STYLE_BOLD = 0;
    private static final int STYLE_ITALIC = 1;
    private static final int STYLE_UNDERLINED = 2;

    // Optional styling button references
    private ToggleButton boldToggle;
    private ToggleButton italicsToggle;
    private ToggleButton underlineToggle;

    // Html image getter that handles the loading of inline images
    private Html.ImageGetter imageGetter;

    private boolean isDeleteCharaters = false;

    // Color
    private int currentColor = -1;
    private EventBack eventBack;


    // interface
    // 创建回调函数
    public interface EventBack {
        public void close();

        public void show();
    }

    public EventBack getEventBack() {
        return eventBack;
    }

    public void setEventBack(EventBack eventBack) {
        this.eventBack = eventBack;
    }

    // 当键盘弹起时
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        // 如果键盘是退回键 关闭 否则显示
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            eventBack.close();
        } else {
            eventBack.show();
        }
        return super.dispatchKeyEvent(event);
    }

    // 构造函数
    public CustomEditText(Context context) {
        super(context);
        initialize();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        // Add a default imageGetter
        imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                return null;
            }
        };

        // Add TextWatcher that reacts to text changes and applies the selected
        // styles
        this.addTextChangedListener(new DWTextWatcher());
    }

    private class DWTextWatcher implements TextWatcher {
        private int beforeChangeTextLength = 0;
        private int appendTextLength = 0;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            int position = Selection.getSelectionStart(CustomEditText.this.getText());
            if (position < 0) {
                position = 0;
            }

            beforeChangeTextLength = position;
            if ((count - after == 1) || (s.length() == 0) && position > 0) { // Delete character
                Editable editable = CustomEditText.this.getText();

                removeForegroundColorSpan(position, editable);
                removeAgsUnderlineSpan(position, editable);
                removeStyleSpan(position, editable, android.graphics.Typeface.ITALIC);
                removeStyleSpan(position, editable, android.graphics.Typeface.BOLD);
            }
        }

        private void removeStyleSpan(int position, Editable editable, int type) {
            StyleSpan previousColorSpan = (StyleSpan) getPreviousForegroundColorSpan(editable, position, StyleSpan.class);
            StyleSpan[] appliedStyles = editable.getSpans(position - 1, position, StyleSpan.class);

            StyleSpan styleSpan = null;
            for (StyleSpan span : appliedStyles) {
                if (span.getStyle() == type) {
                    styleSpan = span;
                }
            }

            if (styleSpan != null && previousColorSpan == null) {
                int styleStart = editable.getSpanStart(styleSpan);
                int styleEnd = editable.getSpanEnd(styleSpan);

                editable.removeSpan(styleSpan);
                if (styleStart < (position - 1)) {
                    editable.setSpan(new StyleSpan(type), styleStart, position - 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // We need to split the span
                if (styleEnd > position) {
                    editable.setSpan(new StyleSpan(type), position, styleEnd,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private void removeAgsUnderlineSpan(int position, Editable editable) {
            AgsUnderlineSpan previousColorSpan = (AgsUnderlineSpan) getPreviousForegroundColorSpan(editable, position, AgsUnderlineSpan.class);
            AgsUnderlineSpan[] appliedStyles = editable.getSpans(position - 1, position, AgsUnderlineSpan.class);

            if (appliedStyles.length > 0 && previousColorSpan == null) {
                AgsUnderlineSpan colorSpan = (AgsUnderlineSpan) appliedStyles[0];
                int underLineStart = editable.getSpanStart(colorSpan);
                int underLineEnd = editable.getSpanEnd(colorSpan);

                editable.removeSpan(colorSpan);
                if (underLineStart < (position - 1)) {
                    editable.setSpan(new AgsUnderlineSpan(), underLineStart, position - 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // We need to split the span
                if (underLineEnd > position) {
                    editable.setSpan(new AgsUnderlineSpan(), position, underLineEnd,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private void removeForegroundColorSpan(int position, Editable editable) {
            ForegroundColorSpan previousColorSpan = (ForegroundColorSpan) getPreviousForegroundColorSpan(editable, position, ForegroundColorSpan.class);
            ForegroundColorSpan[] appliedStyles = editable.getSpans(position - 1, position, ForegroundColorSpan.class);

            if (appliedStyles.length > 0 && appliedStyles[0] != null && previousColorSpan != null
                    && previousColorSpan.getForegroundColor() !=  appliedStyles[0].getForegroundColor()) {
                ForegroundColorSpan colorSpan = (ForegroundColorSpan) appliedStyles[0];
                int colorStart = editable.getSpanStart(colorSpan);
                int colorEnd = editable.getSpanEnd(colorSpan);

                editable.removeSpan(colorSpan);
                if (colorStart < (position - 1)) {
                    editable.setSpan(new AgsUnderlineSpan(), colorStart, position - 1,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // We need to split the span
                if (colorEnd > position) {
                    editable.setSpan(new AgsUnderlineSpan(), position, colorEnd,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private Object getPreviousForegroundColorSpan(Editable editable, int position, Class<?> clss) {
            if (position - 2 >= 0) {
                Object[] nextSpans = editable.getSpans(position - 2, position - 1, clss);
                if (nextSpans.length > 0) {
                    return nextSpans[0];
                }
            }
            return null;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            isDeleteCharaters = count == 0 ? true : false;
            // Remove all span when EditText is empty
            if (CustomEditText.this.getText().toString().isEmpty()) {
                CharacterStyle[] appliedStyles = CustomEditText.this.getText().getSpans(0,
                        CustomEditText.this.getText().length(), CharacterStyle.class);
                for (CharacterStyle characterStyle : appliedStyles) {
                    CustomEditText.this.getText().removeSpan(characterStyle);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 得到改变的文本长度
            // Add style as the user types if a toggle button is enabled
            int position = Selection.getSelectionStart(CustomEditText.this.getText());
            appendTextLength = Math.abs(position - beforeChangeTextLength);

            // 当没有改变时 直接返回
            //XXX: Fixed bold error when text size not change
            if (appendTextLength == 0 || isDeleteCharaters) {
                return;
            }

            // 修正position
            if (position < 0) {
                position = 0;
            }

            if (position > 0) {
                // 更改文字样式范围
                CharacterStyle[] appliedStyles = editable.getSpans(position - 1, position, CharacterStyle.class);

                // 样式范围
                StyleSpan currentBoldSpan = null;
                StyleSpan currentItalicSpan = null;
                AgsUnderlineSpan currentAgsUnderlineSpan = null;
                ForegroundColorSpan currentForegroundColorSpan = null;

                // 循环文字得到各种样式
                // Look for possible styles already applied to the entered text
                for (int i = 0; i < appliedStyles.length; i++) {
                    if (appliedStyles[i] instanceof StyleSpan) {
                        if (((StyleSpan) appliedStyles[i]).getStyle() == android.graphics.Typeface.BOLD) {
                            // Bold style found
                            currentBoldSpan = (StyleSpan) appliedStyles[i];
                        } else if (((StyleSpan) appliedStyles[i]).getStyle() == android.graphics.Typeface.ITALIC) {
                            // Italic style found
                            currentItalicSpan = (StyleSpan) appliedStyles[i];
                        }
                    } else if (appliedStyles[i] instanceof AgsUnderlineSpan) {
                        // Underlined style found
                        currentAgsUnderlineSpan = (AgsUnderlineSpan) appliedStyles[i];
                    } else if (appliedStyles[i] instanceof ForegroundColorSpan) {
                        if (currentForegroundColorSpan == null) {
                            currentForegroundColorSpan = (ForegroundColorSpan) appliedStyles[i];
                        }
                    }
                }

                // 对各种样式的处理
                handleInsertBoldCharacter(editable, position, currentBoldSpan);
                handleInsertItalicCharacter(editable, position, currentItalicSpan);
                handleInsertUnderlineCharacter(editable, position, currentAgsUnderlineSpan);
                handleInsertColorCharacter(editable, position, currentForegroundColorSpan);
            }
        }

        private void handleInsertColorCharacter(Editable editable, int position, ForegroundColorSpan currentForegroundColorSpan) {
            // Handle color
            if (currentForegroundColorSpan != null) {
                if (currentForegroundColorSpan.getForegroundColor() != currentColor) {
                    int colorStart = editable.getSpanStart(currentForegroundColorSpan);
                    int colorEnd = editable.getSpanEnd(currentForegroundColorSpan);

                    if (position == colorEnd) {
                        ForegroundColorSpan nextSpan = getNextForegroundColorSpan(editable, position);
                        if (nextSpan != null) {
                            if (currentColor == nextSpan.getForegroundColor()) {
                                int colorEndNextSpan = editable.getSpanEnd(nextSpan);
                                editable.removeSpan(currentForegroundColorSpan);
                                editable.removeSpan(nextSpan);
                                // set before span
                                editable.setSpan(new ForegroundColorSpan(currentForegroundColorSpan.getForegroundColor()), colorStart,
                                        colorEnd - appendTextLength,
                                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                // set after span
                                editable.setSpan(new ForegroundColorSpan(nextSpan.getForegroundColor()),
                                        position - appendTextLength,
                                        colorEndNextSpan,
                                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                return;
                            }
                        }
                    }
                    editable.removeSpan(currentForegroundColorSpan);
                    if (position - appendTextLength < colorEnd && colorStart != colorEnd) {
                        // Cursor in the text's middle with different color
                        int oldColor = currentForegroundColorSpan.getForegroundColor();

                        if (colorStart < position - appendTextLength) {
                            // Before inserting text
                            editable.setSpan(new ForegroundColorSpan(oldColor), colorStart,
                                    position - appendTextLength,
                                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        }

                        // At inserting
                        editable.setSpan(new ForegroundColorSpan(currentColor), position - appendTextLength,
                                position,
                                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                        if (position < colorEnd) {
                            // After inserting
                            editable.setSpan(new ForegroundColorSpan(oldColor), position, colorEnd,
                                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                        }
                    }else{
                        // Cursor in the end
                        editable.setSpan(new ForegroundColorSpan(currentColor), position - appendTextLength,
                                colorEnd,
                                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    }

                }
            } else if (currentColor != -1) {
                ForegroundColorSpan nextSpan = getNextForegroundColorSpan(editable, position);
                if (nextSpan != null) {
                    int colorEndNextSpan = editable.getSpanEnd(nextSpan);
                    if (currentColor == nextSpan.getForegroundColor()) {
                        editable.removeSpan(nextSpan);
                        // set before span
                        editable.setSpan(new ForegroundColorSpan(nextSpan.getForegroundColor()),
                                position - appendTextLength,
                                colorEndNextSpan,
                                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

                        return;
                    }
                    editable.setSpan(new ForegroundColorSpan(currentColor),
                            position - appendTextLength, position,
                            Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                }

            }
        }

        private ForegroundColorSpan getNextForegroundColorSpan(Editable editable, int position) {
            ForegroundColorSpan nextSpans[] = editable.getSpans(position, position + 1, ForegroundColorSpan.class);
            if (nextSpans.length > 0) {
                return nextSpans[0];
            }
            return null;
        }

        private void handleInsertUnderlineCharacter(Editable editable, int position, AgsUnderlineSpan currentAgsUnderlineSpan) {
            // Handle the underlined style toggle button if it's present
            if (underlineToggle != null && underlineToggle.isChecked()
                    && currentAgsUnderlineSpan == null) {
                editable.setSpan(new AgsUnderlineSpan(), position - appendTextLength, position,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            } else if (underlineToggle != null && !underlineToggle.isChecked()
                    && currentAgsUnderlineSpan != null) {
                int underLineStart = editable.getSpanStart(currentAgsUnderlineSpan);
                int underLineEnd = editable.getSpanEnd(currentAgsUnderlineSpan);

                editable.removeSpan(currentAgsUnderlineSpan);
                if (underLineStart <= (position - appendTextLength)) {
                    editable.setSpan(new AgsUnderlineSpan(), underLineStart, position - appendTextLength,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // We need to split the span
                if (underLineEnd > position) {
                    editable.setSpan(new AgsUnderlineSpan(), position, underLineEnd,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private void handleInsertItalicCharacter(Editable editable, int position, StyleSpan currentItalicSpan) {
            // Handle the italics style toggle button if it's present
            if (italicsToggle != null && italicsToggle.isChecked() && currentItalicSpan == null) {
                editable.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), position - appendTextLength,
                        position,
                        Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            } else if (italicsToggle != null && !italicsToggle.isChecked()
                    && currentItalicSpan != null) {
                int italicStart = editable.getSpanStart(currentItalicSpan);
                int italicEnd = editable.getSpanEnd(currentItalicSpan);

                editable.removeSpan(currentItalicSpan);
                if (italicStart <= (position - appendTextLength)) {
                    editable.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC),
                            italicStart, position - appendTextLength,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // Split the span
                if (italicEnd > position) {
                    editable.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), position,
                            italicEnd,
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }

        private void handleInsertBoldCharacter(Editable editable, int position, StyleSpan currentBoldSpan) {
            // Handle the bold style toggle button if it's present
            if (boldToggle != null) {
                if (boldToggle.isChecked() && currentBoldSpan == null) {
                    // The user switched the bold style button on and the
                    // character doesn't have any bold
                    // style applied, so we start a new bold style span. The
                    // span is inclusive,
                    // so any new characters entered right after this one
                    // will automatically get this style.
                    editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                            position - appendTextLength, position,
                            Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                } else if (!boldToggle.isChecked() && currentBoldSpan != null) {
                    // The user switched the bold style button off and the
                    // character has bold style applied.
                    // We need to remove the old bold style span, and define
                    // a new one that end 1 position right
                    // before the newly entered character.
                    int boldStart = editable.getSpanStart(currentBoldSpan);
                    int boldEnd = editable.getSpanEnd(currentBoldSpan);

                    editable.removeSpan(currentBoldSpan);
                    if (boldStart <= (position - appendTextLength)) {
                        editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                                boldStart, position - appendTextLength,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                    // The old bold style span end after the current cursor
                    // position, so we need to define a
                    // second newly created style span too, which begins
                    // after the newly entered character and
                    // ends at the old span's ending position. So we split
                    // the span.
                    if (boldEnd > position) {
                        editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
                                position, boldEnd,
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                }
            }
        }
    }
}
