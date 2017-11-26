# difference between autocompletetextview and multiautocompletetextview
1. autoCompleteTextView only match key word once
2. multiAutoCompleteTextView can match key word many times through separating by separator like comma 

# Android AutoCompleteTextView Control example 
activity_main.xml
```xml
    <AutoCompleteTextView
        android:completionThreshold="3"
        android:id="@+id/autoCompleteTextView1"
        android:hint="please input the search keyword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

mainactivity.java
```java 
    private AutoCompleteTextView acTextView;
    private String[] res = {"beijing1","beijing2","shanghai1","shanghai2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 1. initial controls
         * 2. need an adapter
         * 3. Create dataset -> which match with Key word
         * 4. binding adapter & autoCompleteTextView
         */

        acTextView = findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,res);
        acTextView.setAdapter(adapter);
    }
```

# multiautocompletetextview android
activity_main.xml
```xml
    <MultiAutoCompleteTextView
        android:id="@+id/multiAutoCompleteTextView1"
        android:hint="please input receivers"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
```

mainactivity.java
```java 
    private MultiAutoCompleteTextView macTextView;
    private String[] res = {"beijing1","beijing2","shanghai1","shanghai2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** multiAutoCompleteTextView
         * 1. initial controls
         * 2. need an adapter
         * 3. Create dataset -> which match with Key word
         * 4. binding adapter & autoCompleteTextView
         * 5. setup separator
         */

        macTextView = findViewById(R.id.multiAutoCompleteTextView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,res);
        macTextView.setAdapter(adapter);
        // set comma as separator
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
```
