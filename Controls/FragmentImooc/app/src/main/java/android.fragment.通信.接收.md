# fragment -> Activity
1. fragment 里增加监听接口 
```java
    public MyListener myListener;
    public interface MyListener{
        public void thank(String code);
    }
```

1. Activity implement 接口 并实现接收后的行为
```java
public class CommuteActivity extends Activity implements CommuteFragment.MyListener{
        @Override
        public void thank(String code) {
            Toast.makeText(CommuteActivity.this, "已成功接收到" + code + ",客气了!", Toast.LENGTH_SHORT).show();
        }
}
```

1. onAttach的时候接收Activity并转为接口 
```java
    @Override
    public void onAttach(Activity activity) {
        myListener = (MyListener) activity;
        super.onAttach(activity);
    }
```

1. 通过转换的Listener实现行为
```java
        myListener.thank(code);
```