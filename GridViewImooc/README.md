# 控件/GridView simple gridview example in android
数据源(集合) -> 适配器(SimpleAdapter) -> 视图界面(GridView)

属性 
    android:numColumns="auto_fit" 每一行显示多少列 android:numColumns="3"
    android:horizontalSpacing="10dp" 两列之间的间距
    android:verticalSpacing="10dp"  两行之间的间距

1. 准备数据源
2. 新建适配器(SimpleAdapter)
3. GridView加载适配器
4. GridView配置事件监听器 (OnItemClickListener)

```java 
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private List<Map<String,Object>> datalist;
    private SimpleAdapter adapter;
    private int[] icon = {
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_assistant_black_24dp,
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_assistant_black_24dp,
            R.drawable.ic_android_black_24dp,
            R.drawable.ic_announcement_black_24dp,
            R.drawable.ic_assessment_black_24dp,
            R.drawable.ic_assistant_black_24dp
    };
    private String[] iconName = {"通信录", "日历", "照相机", "通信录", "日历", "照相机", "通信录", "日历", "照相机", "通信录", "日历", "照相机"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        /**
         * 1. 准备数据源
         * 2. 新建适配器(SimpleAdapter)
         * 3. GridView加载适配器
         * 4. GridView配置事件监听器 (OnItemClickListener)
         */
        datalist = new ArrayList<Map<String, Object>>();
        adapter = new SimpleAdapter(this,getData() ,R.layout.item, new String[]{"image", "text"}, new int[]{R.id.image, R.id.text});
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData() {
        for (int i=0;i<icon.length;i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image",icon[i]);
            map.put("text", iconName[i]);
            datalist.add(map);
        }
        return datalist;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "我是" + iconName[position], Toast.LENGTH_SHORT).show();
    }
}
```