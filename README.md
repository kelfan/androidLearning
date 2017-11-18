# Android Learning
Codes and examples of android applications.

# test device = Samsung note3
1080*1920 pixels
Android 5.1.1, API 22

# myfirstapp = intent
- This simplest example includes about how to transfer message between two activities;
![app](https://developer.android.com/training/basics/firstapp/images/screenshot-activity2.png)

# ReadFile = reading text file
[Read text file from assets folder in Android Studio](https://www.youtube.com/watch?v=1CHDASXojNQ)

# user interface hierarchy
![user interface hierarchy](https://developer.android.com/training/basics/firstapp/building-ui.html#open)

# Settings and Preferences
![](assets/README-480d03b1.png)
[AS #35: Create Settings and Preferences @Android Studio](https://www.youtube.com/watch?v=jzpI9vdeV1A)

# files/app>java>com.examples.name.app>MainActivity = 主页面
# files/app>res>layout>activity_main.xml = 主页面布局
# files/app>res>values>strings.xml = 存储一些共用的字符串


# Match Constraints = 横向自动延伸

# file 文件的各种操作
[File google](https://developer.android.com/reference/java/io/File.html)
[彻底理解android中的内部存储与外部存储](http://blog.csdn.net/u012702547/article/details/50269639)
- Environment.getExternalStorageDirectory(); // 获取sdcard根目录

# Android必备知识点- Android文件（File）操作
[Android必备知识点- Android文件（File）操作](http://www.imooc.com/article/14521)

AndroidManifest.xml
```xml
<!-- acquire write and read permission -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
向文件写入一些文本
```java
String filename = "myfile"; String string = "Hello world!"; FileOutputStream outputStream; try { outputStream = openFileOutput(filename, Context.MODE_PRIVATE); outputStream.write(string.getBytes()); outputStream.close(); } catch (Exception e) { e.printStackTrace(); }
```
需要缓存某些文件
```java
public File getTempFile(Context context, String url) {
  File file;
   try { String fileName = Uri.parse(url).getLastPathSegment();
      file = File.createTempFile(fileName, null, context.getCacheDir());
     } catch (IOException e) {
       // Error while creating file
     } return file;
    }
```
**将文件保存在外部存储中**
确定存储可用性
```java
* Checks if external storage is available for read and write */ public boolean isExternalStorageWritable() { String state = Environment.getExternalStorageState(); if (Environment.MEDIA_MOUNTED.equals(state)) { return true; } return false; }
```
```java
/* Checks if external storage is available to at least read */ public boolean isExternalStorageReadable() { String state = Environment.getExternalStorageState(); if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) { return true; } return false; }
```
将公共文件保存在外部存储设备上
```java
public File getAlbumStorageDir(String albumName) {
// Get the directory for the user's public pictures directory.
File file = new File(Environment.getExternalStoragePublicDirectory(
Environment.DIRECTORY_PICTURES), albumName);
if (!file.mkdirs()) {
Log.e(LOG_TAG, "Directory not created");
}
return file;
}
```
创建个人相册的目录
```java
public File getAlbumStorageDir(Context context, String albumName) {
// Get the directory for the app's private pictures directory.
File file = new File(context.getExternalFilesDir(
Environment.DIRECTORY_PICTURES), albumName);
if (!file.mkdirs()) {
Log.e(LOG_TAG, "Directory not created");
}
return file;
}
```
删除文件
```java
// 您应始终删除不再需要的文件。删除文件最直接的方法是让打开的文件参考自行调用 delete()。
myFile.delete();
// 如果文件保存在内部存储中，您还可以请求 Context 通过调用 deleteFile() 来定位和删除文件：
myContext.deleteFile(fileName);
```

# shortcut/Alt + Enter = 错误快速修复 (or Option + Enter on Mac)
# shortcut/Alt + insert = constructor getter setter
# shortcut/Alt + 单击 = 竖着选多个元素
![](assets/README-b3ac89d7.png)
# shortcut/Alt + shift + 单击 = 多选几次
# shortcut/Alt + j or ctrl + g = 多选相同元素
![](assets/README-88d6706d.png)

# 05/Write to SD card
AndroidMainfest.xml -> manifest -> before application
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
```java
public void generateNoteOnSD(Context context, String sFileName, String sBody) {
    try {
        File root = new File(Environment.getExternalStorageDirectory(), "Notes");
        if (!root.exists()) {
            root.mkdirs();
        }
        File gpxfile = new File(root, sFileName);
        FileWriter writer = new FileWriter(gpxfile);
        writer.append(sBody);
        writer.flush();
        writer.close();
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```
I think you have to replace this line:
```java
FileOutputStream fileOutputStream = new FileOutputStream(file);
```
with this:
```java
FileOutputStream fileOutputStream = new FileOutputStream(file, true);
```
where if you set boolen true, you say that you want to write appending text.

#  android:onClick
method must declare the following:
- Public access
- A void return value
- A View as the only parameter (it is the View object that was clicked)

# Toast = pop message in a short time
```java
Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();  
// or
Toast toast=Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);  
toast.setMargin(50,50);  
toast.show();  
```

# JRebel = ~~快速调试~~ 已经收费
[ANDROID 高效调试神器 JREBEL](http://stormzhang.com/2016/11/21/use-jrebel-for-android/)
打开 Preference -> Plugins -> Browse Repositories ，搜索 JRebel ，直接点击安装。
![](assets/README-1f94faf1.png)
一般我们只需要第一次安装的时候使用第一个按钮运行一次，之后代码变动，只需要点击第三个按钮，你的真机或者模拟器很快就展示出来修改代码之后的效果。

#  Intent = 两个原件之间传递信息
An Intent is an object that provides runtime binding between separate components, such as two activities.

```java
public void sendMessage(View view){
    // intent(传递的内容，传递过去的对象)
    Intent intent = new Intent(this, DisplayMessageActivity.class);
    EditText editText = (EditText) findViewById(R.id.editText);
    String message = editText.getText().toString();
    // putExtra(信息存储的key，加入传递的信息内容)
    intent.putExtra(EXTRA_MESSAGE, message);
    // starts an instance of the DisplayMessageActivity 创建要传递过去的对象实例
    startActivity(intent);
}
```

# add different languages
https://developer.android.com/training/basics/supporting-devices/languages.html
把不同语言的资源放到下面格式的文件夹中就可以，然后使用相同的name使用
`<resource type>-b+<language code>[+<country code>]`

```
MyProject/
    res/
       values/
           strings.xml
       values-b+es/
           strings.xml
       mipmap/
           country_flag.png
       mipmap-b+es+ES/
           country_flag.png
```

#  unicodeWrap() = 对于不同语言显示的矫正
The unicodeWrap() method detects the direction of a string and wraps it in Unicode formatting characters that declare that direction. Because the "15" now appears inside text that is declared as LTR, it's displayed in the correct position:

האם התכוונת ל 15 Bay Street, Laurel, CA?

The following code snippet demonstrates how to use unicodeWrap():
```java
String mySuggestion = "15 Bay Street, Laurel, CA";
BidiFormatter myBidiFormatter = BidiFormatter.getInstance();

// The "did_you_mean" localized string resource includes
// a "%s" placeholder for the suggestion.
String.format(R.string.did_you_mean,
        myBidiFormatter.unicodeWrap(mySuggestion));
```

# String.format() = 数字转字符串
```java
String myIntAsString = String.format("%d", myInt);
```

# xml/LinearLayout = 堆栈排列布局
 http://blog.csdn.net/llping2011/article/details/9992941
```xml
 <LinearLayout
        android:orientation="horizontal" >
        <EditText
            android:layout_weight="1">
        </EditText>
    </LinearLayout>
```

# xml/EditText
```xml
<EditText
    android:id="@+id/msg"
    android:inputType="number"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="top"
    android:hint="@string/to"
    android:inputType="textMultiLine"
    android:minLines="5"
    android:text="">
</EditText>
```


# Android 在软键盘弹出时将布局上移，不掩盖控件
http://www.jianshu.com/p/8c98df35d368
可以在`AndroidMainfest.xml`给该`Activity`加入一个属性`windowSoftInputMode`，就可以让系统在弹起键盘时自动调整界面。如果没有`stateHidden`会自动弹出键盘。
```xml
<activity android:name=".ui.activity.LoginActivity"
android:windowSoftInputMode="adjustResize|stateHidden" />
```

# sources
- Android 经典项目开发实战
- [Android Studio Tutorial - Alarm Manager](https://www.youtube.com/watch?v=-Q5MFwgXIcc)
- [Xamarin Android Tutorial - Linear Layout](https://www.youtube.com/watch?v=Wj-WT4uWlKA&list=PLaoF-xhnnrRVglZztNl99ih76fvBOLMe8)
- [Android Studio Tutorial - Text Recognition using Google Vision](https://www.youtube.com/watch?v=7qw-zl9XGd4&list=PLaoF-xhnnrRWHtmb8ZGmu8N4Wl2Zr26V7&t=5)
- []()
- []()
- []()
- []()
- []()
- []()

# Raturns all available SD-Cards in the system (include emulated)
```java
private static final Pattern DIR_SEPORATOR = Pattern.compile("/");

/**
 * Raturns all available SD-Cards in the system (include emulated)
 *
 * Warning: Hack! Based on Android source code of version 4.3 (API 18)
 * Because there is no standart way to get it.
 * TODO: Test on future Android versions 4.4+
 *
 * @return paths to all available SD-Cards in the system (include emulated)
 */
public static String[] getStorageDirectories()
{
    // Final set of paths
    final Set<String> rv = new HashSet<String>();
    // Primary physical SD-CARD (not emulated)
    final String rawExternalStorage = System.getenv("EXTERNAL_STORAGE");
    // All Secondary SD-CARDs (all exclude primary) separated by ":"
    final String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");
    // Primary emulated SD-CARD
    final String rawEmulatedStorageTarget = System.getenv("EMULATED_STORAGE_TARGET");
    if(TextUtils.isEmpty(rawEmulatedStorageTarget))
    {
        // Device has physical external storage; use plain paths.
        if(TextUtils.isEmpty(rawExternalStorage))
        {
            // EXTERNAL_STORAGE undefined; falling back to default.
            rv.add("/storage/sdcard0");
        }
        else
        {
            rv.add(rawExternalStorage);
        }
    }
    else
    {
        // Device has emulated storage; external storage paths should have
        // userId burned into them.
        final String rawUserId;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
        {
            rawUserId = "";
        }
        else
        {
            final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
            final String[] folders = DIR_SEPORATOR.split(path);
            final String lastFolder = folders[folders.length - 1];
            boolean isDigit = false;
            try
            {
                Integer.valueOf(lastFolder);
                isDigit = true;
            }
            catch(NumberFormatException ignored)
            {
            }
            rawUserId = isDigit ? lastFolder : "";
        }
        // /storage/emulated/0[1,2,...]
        if(TextUtils.isEmpty(rawUserId))
        {
            rv.add(rawEmulatedStorageTarget);
        }
        else
        {
            rv.add(rawEmulatedStorageTarget + File.separator + rawUserId);
        }
    }
    // Add all secondary storages
    if(!TextUtils.isEmpty(rawSecondaryStoragesStr))
    {
        // All Secondary SD-CARDs splited into array
        final String[] rawSecondaryStorages = rawSecondaryStoragesStr.split(File.pathSeparator);
        Collections.addAll(rv, rawSecondaryStorages);
    }
    return rv.toArray(new String[rv.size()]);
}
```

# acquire different SD card directory
```java
// Primary physical SD-CARD (not emulated)
final String rawExternalStorage = System.getenv("EXTERNAL_STORAGE");
File root = new File(Environment.getExternalStorageDirectory(), "05fanNotes");
// All Secondary SD-CARDs (all exclude primary) separated by ":"
final String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");
File root = new File(System.getenv("SECONDARY_STORAGE"));
// Primary emulated SD-CARD
final String rawEmulatedStorageTarget = System.getenv("EMULATED_STORAGE_TARGET");
```

# adapater
adapter常用来管理数据。比如列表的数据，网格的数据。

```java
public class PersonAdapter extends BaseAdapter {
    Activity activity;
    List<Person> lstPersons;
    LayoutInflater inflater;
    EditText edtId, edtName, edtEmail;

    public PersonAdapter(Activity activity, List<Person> lstPersons, EditText edtId, EditText edtName, EditText edtEmail) {
        this.activity = activity;
        this.lstPersons = lstPersons;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.edtId = edtId;
        this.edtName = edtName;
        this.edtEmail = edtEmail;
    }

    @Override
    public int getCount() {
        return lstPersons.size();
    }

    @Override
    public Object getItem(int i) {
        return lstPersons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lstPersons.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View rowView;
        rowView=inflater.inflate(R.layout.row, null);
        final TextView txtRowId,txtRowName,txtRowEmail;
        txtRowId = (TextView) rowView.findViewById(R.id.txtRowId);
        txtRowName = (TextView) rowView.findViewById(R.id.txtRowName);
        txtRowEmail = (TextView) rowView.findViewById(R.id.txtRowEmail);

        txtRowId.setText(""+lstPersons.get(i).getId());
        txtRowName.setText(""+lstPersons.get(i).getName());
        txtRowEmail.setText(""+lstPersons.get(i).getEmail());

        rowView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                edtId.setText(""+txtRowId.getText());
                edtName.setText(""+txtRowName.getText());
                edtEmail.setText(""+txtRowEmail.getText());
            }
        });

        return rowView;
    }
}
```

# 设置头部格式
点击设置—>Editor–>File and code Templates –>Includes—>File Header
```java
/**
 * 作者：author
 * 时间：${DATE}:${TIME}
 * 邮箱：
 * 说明：
 */
```

# inflater/layoutInflater = 获取layout下的xml文件
https://zhidao.baidu.com/question/2205143519905210068.html
在实际开发中LayoutInflater这个类还是非常有用的，它的作用类似于findViewById()。不同点是LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化；而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)。
具体作用：
1. 对于一个没有被载入或者想要动态载入的界面，都需要使用LayoutInflater.inflate()来载入；
2. 对于一个已经载入的界面，就可以使用Activiyt.findViewById()方法来获得其中的界面元素。

```java
LayoutInflater inflater;

public View getView(int i, View convertView, ViewGroup parent) {
    View rowView;
    rowView=inflater.inflate(R.layout.row, null);
    final TextView txtRowId,txtRowName,txtRowEmail;
    txtRowId = (TextView) rowView.findViewById(R.id.txtRowId);
    txtRowName = (TextView) rowView.findViewById(R.id.txtRowName);
    txtRowEmail = (TextView) rowView.findViewById(R.id.txtRowEmail);

    txtRowId.setText(""+lstPersons.get(i).getId());
    txtRowName.setText(""+lstPersons.get(i).getName());
    txtRowEmail.setText(""+lstPersons.get(i).getEmail());

    rowView.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            edtId.setText(""+txtRowId.getText());
            edtId.setText(""+txtRowName.getText());
            edtId.setText(""+txtRowEmail.getText());
        }
    });

    return rowView;
}
```

# add snippets/live templates
https://stackoverflow.com/questions/16857108/code-snippets-in-android-studio
Go to File > Settings > Editor > Live Templates. Then click on the different options to see what they do.
![](assets/README-4874a217.png)
Here are some interesting ones:

- foreach
```java
for ($i$ : $data$) {
    $cursor$
}
```
- Toast
```java
android.widget.Toast.makeText($className$.this, "$text$", Toast.LENGTH_SHORT).show();
```
- todo
```java
// TODO: $date$ $todo$
```
- logi
```java
android.util.Log.i(TAG, "$METHOD_NAME$: $content$");
```
The words surrounded by `$` signs are places where things will be filled in automatically from the context or where the user can tab through to fill them in.

# 对象不为空null并且不等于""空字符
```java
sql!=null && !"".equals(sql)
```

# ConstantValues put
```java
ContentValues values = new ContentValues();
values.put(Constant._ID, 3); // put(表示插入数据库的字段,表示插入该字段的具体值)
values.put(Constant.NAME, "张山");
values.put(Constant.AGE,30);
```

# data type 数据类型
integer varchar(10)  float double char(10) text

# debug/ android device monitor
- 位置
  - tools -> android -> android device monitor
- sqlite 数据库位置
  - data -> data -> 软件包名 -> database -> .db文件

# basic SQL
```sql
创建表
create table 表名(字段名称 数据类型 约束, 字段名称 ...)

删除表
drop table 表名

插入数据
insert into 表名[字段,字段] values(值1,值2..)

修改数据
update 表名 set 字段=新值 where 修改的条件 _id=1

删除数据
delete from 表名 where 删除的条件

查询语句
select 字段名 from 表名 where 查询条件 group by 字段 having 筛选条件 order by 排序字段

select _id,name from person
select * from person where _id=1
select * from person where _id<>1
select * from person where _id=1 and age>18
select * from person where name like "%小%" -- 中间带"小"的数据
select * from person where name like "_小%" -- 一个字符后是"小"然后任意字符
select * from person where name is null
select * from person where age between 10 and 20
select * from person where age>18 order by _id
```


# id to String.valueOf()
```java
new String[]{String.valueOf(person.getId())};
```

# Date to String
```java
cmd.Parameters.AddWithValue("@endDate", String.Format("{0:yyyy/MM/dd HH:mm:ss}", this.EndDate)); // <- this.EndDate is a DateTime type
```

# SQLiteOpenHelper
SQLiteOpenHelper  ->帮助类
onCreate()        ->创建方法
onUpgrade()       ->数据库升级方法
onOpen()          ->打开数据库方法

# SQLiteOpenHelper/example
```java
public class DatabaseHelper extends SQLiteOpenHelper{
    //database
    private static final int DATABASE_VER=1;
    private static final String DATABASE_NAME="EMDTDev";

    //table
    private static final String TABLE_NAME="Persons";
    private static final String KEY_ID="Id";
    private static final String KEY_NAME="Name";
    private static final String KEY_EMAIL="Email";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE"+TABLE_NAME+"("
                +KEY_ID+" INTEGER PRIMARY KEY,"
                +KEY_NAME+" TEXT"
                +KEY_EMAIL+" TEXT"
                +")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    // CRUD Persons
    public void addPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,person.getName());
        values.put(KEY_EMAIL,person.getName());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updatePerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,person.getName());
        values.put(KEY_EMAIL,person.getEmail());

        return db.update(TABLE_NAME,values,KEY_ID+" =?",new String[] {String.valueOf(person.getId())});
    }

    public void deletePerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID+" =?",new String[]{String.valueOf(person.getId())});
        db.close();
    }

    public Person getPerson(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{KEY_ID, KEY_NAME, KEY_EMAIL},
                KEY_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return new Person(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }

    public List<Person> getAllPerson(){
        List<Person> lstPersons = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do{
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setName(cursor.getString(1));
                person.setEmail(cursor.getString(2));

                lstPersons.add(person);
            }
            while (cursor.moveToNext());
        }
        return lstPersons;
    }
}
```
