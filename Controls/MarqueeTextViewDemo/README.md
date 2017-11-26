# Is there a way to make ellipsize=“marquee” always scroll?

## activity_main.xml
```xml
<com.kelfan.marqueetextviewdemo.MarqueeText
    android:singleLine="true"
    android:ellipsize="marquee"
    android:focusable="true"
    android:focusableInTouchMode="true"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/long_text" />

<com.kelfan.marqueetextviewdemo.MarqueeText
    android:singleLine="true"
    android:ellipsize="marquee"
    android:focusable="true"
    android:focusableInTouchMode="true"
    android:layout_marginTop="10dp"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/long_text" />
```

## MarqueeText.java
```java 
public class MarqueeText extends android.support.v7.widget.AppCompatTextView {


    public MarqueeText(Context context) {
        super(context);
    }

    public MarqueeText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
```
