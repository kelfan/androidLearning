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
