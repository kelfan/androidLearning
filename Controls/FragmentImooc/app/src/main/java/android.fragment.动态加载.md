# fragment 事务 
add
remove
replace: 替换fragment 
addToBackStack: 返回时可以返回到前一个存进去的fragment 
commit: 提交到Activity 

# process 
1. 创建fragment xml file 
```xml
<?xml version="1.0" encoding="utf-8"?>
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

1. 创建 fragment Class 
```java
public class DynamicFragment extends Fragment {
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
        textView.setText("动态加载Fragment");

        return view;
    }
}
```

1. 调整 container xml 
参考下面的 id 为 frame的layout 
```xml
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.kelfan.fragmentimooc.MainActivity">

    <TextView
        android:id="@+id/message"
        android:layout_width="wrap_content"
        android:layout_height="19dp"
        android:layout_marginEnd="8dp"
        android:layout_marginLeft="@dimen/activity_horizontal_margin"
        android:layout_marginTop="8dp"
        android:text="@string/title_home"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <LinearLayout
        android:id="@+id/frame"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

    </LinearLayout>

    <android.support.design.widget.BottomNavigationView
        android:id="@+id/navigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="0dp"
        android:layout_marginStart="0dp"
        android:background="?android:attr/windowBackground"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:menu="@menu/navigation" />

</android.support.constraint.ConstraintLayout>
```

1. 改变 Activity 
```java
    mTextMessage.setText("动态加载");
    DynamicFragment dynamicFragment = new DynamicFragment();
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    // 第一个参数是 装fragment的元素Id, 第二个参数是 fragment对象
    fragmentTransaction.add(R.id.frame, dynamicFragment);
    // 可以让fragment可以返回到上一个
    
    // 添加完后就是提交
    fragmentTransaction.commit();
    return true;
```

