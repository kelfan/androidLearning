1. Activity xml 中添加 fragment 组件 
```xml
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

    <fragment
        android:id="@+id/fragment"
        android:name="com.kelfan.fragmentimooc.StaticCommuteFragment"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
    
</LinearLayout>
```

1. fragment 中添加 属性 get/set 会对回来的属性处置 
```java
public class StaticCommuteFragment extends Fragment {

    private String aaa;

    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

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
        Button button = view.findViewById(R.id.button);
        textView.setText("静态加载Fragment");
        button.setText("获取内容");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aaa = getAaa();
                Toast.makeText(getActivity(), "value=" + aaa, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
```

1. Activity 中对 fragment进行处理 
```java
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragmentById = fragmentManager.findFragmentById(R.id.fragment);
        StaticCommuteFragment staticCommuteFragment = (StaticCommuteFragment) fragmentById;
        staticCommuteFragment.setAaa("fragment 静态传值");
```