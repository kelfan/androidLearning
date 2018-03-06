# general ways
- activity --> fragment:
    - 在activity中创建bundle数据包，并调用fragment的setArguments（bundle bundle）方法
- fragment --> activity：
    - 需要在fragment中定义一个内部回调接口，再让包含该fragment的activity实现该回调接口。fragment调用方法传递给activity

# 方法
1. getActivity 获取所在activity
1. FragmentManager的findFragmentById 或 findFragmentByTag 获取Fragment

# activity --> fragment
1. 创建 fragment_commute.xml 用来展示 结构 
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

1. 创建 activity_commute.xml 来发送
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/layout"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
    
    <Button
        android:text="发送"
        android:id="@+id/send"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    
</LinearLayout>
```

1. 添加动作到activity 
```java
public class CommuteActivity extends Activity {

    private EditText editText;
    private Button send;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commute);
        editText = findViewById(R.id.editText);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                CommuteFragment commuteFragment=new CommuteFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", text);
                commuteFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.layout, commuteFragment, "commuteFragment");
                fragmentTransaction.commit();
                Toast.makeText(CommuteActivity.this, "向Fragment发送数据" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

1. 添加反应在Fragment里 
```java
public class CommuteFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commute, container, false);
        TextView textView=view.findViewById(R.id.text);
        String text = getArguments().get("name").toString();
        textView.setText(text);
        Toast.makeText(getActivity(), "已成功接受到" + text, Toast.LENGTH_SHORT).show();
        return view;
    }
}

```