# 静态加载Fragment 
1. 创建Fragment.xml 
```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

1. 创建Fragment Class 
```java
public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // layout 布局文件转换成View对象

        /**
         * resource: Fragment 需要加载的布局文件
         * Root: 加载Layout的父ViewGroup
         * attactToRoot: False, 不返还父ViewGroup
         */
        View view = inflater.inflate(R.layout.fragment, container, false);
        TextView textView = view.findViewById(R.id.text);
        textView.setText("静态加载Fragment");

        return view;
    }
}
```

1. 创建 container.xml 去包含Fragment 
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <fragment
        android:id="@+id/fragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:name="com.kelfan.fragmentimooc.StaticFragment" />

</LinearLayout>
```

1. Activity中初始化调用 
```java
public class MainActivity2 extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        textView = findViewById(R.id.text);
        Button button = findViewById(R.id.button);
        button.setText("改变");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("textView改变了");
            }
        });
    }
}
```

1. 添加Activity信息到注册manifest 
```xml
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity
            android:name=".MainActivity"
            android:label="@string/app_name">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity android:name=".MainActivity2" />
    </application>
```

# error/ must specify unique android Id 
 - Caused by: java.lang.IllegalArgumentException: Binary XML file line #0: Must specify unique android:id, android:tag, or have a parent with an id for com.kelfan.fragmentimooc.StaticFragment
    - 给对应element给与唯一的Id 