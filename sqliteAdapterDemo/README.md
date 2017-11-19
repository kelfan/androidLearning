# Android基础-初识SQLite github codes
这是根据视频编写的源码.
- 是一个比较规范和良好编程习惯的例子;
- simpleCursorAdapter 和 cursorAdapter: 适合只用于展示数据库内容

# sqlite/simpleCursorAdapter
```java
/**
 * 演示查询sdcard中数据库中的数据适配到listView中
 */

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);

        // 1. 获取数据库查询的数据
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "info.db";

        /*
        openDatabase(
            String path 表示要打开的数据库的路径,
            cursorFactory 游标工厂 可以为空,
            int flags 表示打开数据库的操作 如只读)
         */
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor=db.rawQuery("select * from "+ Constant.TABLE_NAME,null);

        // 2. 讲数据加载到适配器中
        /*
        SimpleCursorAdapter(
            context context 上下文对象,
            int layout 表示 适配器控件中每项item的布局id,
            Cursor c 表示Cursor数据源,
            string[] from 表示Cursor中数据表字段的数组 显示哪些字段就写哪些,
            int[] to 表示展示字段对应值的控件资源id 表示要展示到的控件上,
            int flags 设置适配器的标记 SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER 观察者模式=有一个观察者专门观察改变 一旦发现改变就做出应对)
         */
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.list_item, cursor,
                new String[]{Constant._ID, Constant.NAME, Constant.AGE}, new int[] {R.id.tv_id, R.id.tv_name, R.id.tv_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        // 3. 将适配器的数据加载到控件
        // 使用simpleCursorAdapter, 主键名字必须是 id, 否则报异常
        lv.setAdapter(adapter);

    }
}

```
# sqlite/cursorAdapter
```java
public class CursorAdapterActivity extends AppCompatActivity {
    private ListView lv;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle saveInsanceState){
        super.onCreate(saveInsanceState);
        lv = (ListView) findViewById(R.id.lv);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "info.db";
        db = SQLiteDatabase.openDatabase(path, null,SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = db.rawQuery("select * from "+ Constant.TABLE_NAME,null);
        MyCursorAdapter adapter = new MyCursorAdapter(this,cursor,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(adapter);
    }

    // cursorAdpater 是抽象类,必须继承才可以实例化

    /**
     * 以内部类形式定义适配器
     */
    public class MyCursorAdapter extends CursorAdapter{
        // 必须定义构造方法
        public MyCursorAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }

        /**
         * 表示创建适配器控件中每个item对应的view对象
         * @param context 上下文
         * @param cursor 数据源cursor对象
         * @param viewGroup 当前item的父布局
         * @return 每项item的view对象
         */
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View view = LayoutInflater.from(CursorAdapterActivity.this).inflate(R.layout.list_item,null);
            return view;
        }

        /**
         * 通过newView() 方法确定了每个item展示的view对象 在bindview()中对布局中的控件进行填充
         * @param view 由newView() 返回每项view对象
         * @param context 上下文
         * @param cursor 数据源cursor对象
         */
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            TextView tv_age = (TextView) view.findViewById(R.id.tv_age);

            // setText 如果参数是数字就是当成资源id去找资源而不是输出,所以最后+"",转为字符串
            tv_id.setText(cursor.getString(cursor.getColumnIndex(Constant._ID)) + "");
            tv_id.setText(cursor.getString(cursor.getColumnIndex(Constant.NAME))+"");
            tv_id.setText(cursor.getString(cursor.getColumnIndex(Constant.AGE))+"");


        }


    }
}
```
# source
[Android基础教程-SQLite高级操作 @Summer321](http://www.imooc.com/learn/749)
