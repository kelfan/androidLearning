# android spinner example source code dropdown
```java 
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textView;
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter adapter;

    private Spinner spinner2;
    private SimpleAdapter adapter2;
    private List<Map<String,Object>> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        spinner = findViewById(R.id.spinner);
        textView.setText("你选择的城市是");

        // 1.设置数据源
        list = new ArrayList<String>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");

        //2. 新建ArrayAdapter (数组适配器)
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);

        // 3.adapter 设置一个下拉列表样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //4. spinner加载适配器
        spinner.setAdapter(adapter);
        spinner.setSelection(2);

        //5.spinner 设置监听器
        spinner.setOnItemSelectedListener(this);

        //1. acquire elements
        datalist = new ArrayList<Map<String, Object>>();
        getData();
        spinner2 = findViewById(R.id.spinner2);
        //2. set adapter
        adapter2 = new SimpleAdapter(this, datalist, R.layout.item,  new String[]{"image","text"}, new int[]{ R.id.image, R.id.text });
        //3. set style Of dropdown spinner
        adapter2.setDropDownViewResource(R.layout.item);
        //4. set adapter To spinner
        spinner2.setAdapter(adapter2);
        //5. add Event response
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textView.setText("你选择的是: "+ adapter.getItem(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("NONE");
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String cityName = adapter.getItem(position).toString();
//        String city = list.get(position);
        textView.setText("你选择的城市是"+cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getData(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("image", R.drawable.ic_launcher_background);
        map.put("text", "北京");
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.drawable.ic_launcher_background);
        map2.put("text", "上海");
        Map<String,Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.drawable.ic_launcher_background);
        map3.put("text", "广州");
        Map<String,Object> map4 = new HashMap<String, Object>();
        map4.put("image", R.drawable.ic_launcher_background);
        map4.put("text", "深圳");

        datalist.add(map);
        datalist.add(map2);
        datalist.add(map3);
        datalist.add(map4);
    }
}
```