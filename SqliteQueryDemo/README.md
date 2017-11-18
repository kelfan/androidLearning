# Android基础-初识SQLite github codes
这是根据视频编写的源码.
- 是一个比较规范和良好编程习惯的例子;
- 主要是对于Sqlite数据库的查询

# cursor To List 游标转为数据列表
```java
/**
 * 把Cursor转换为集合对象
 * @param cursor 需要转换的游标
 * @return 转换后的集合对象
 */
public static List<Person> cursorToList(Cursor cursor){
    List<Person> list=new ArrayList<>();
    // moveToNext(A 如果为true表示还有下一条,否则读取完毕
    while(cursor.moveToNext()){
        // getColumnIndex(String columnName) 根据参数中指定的字段名称获取字段下标
        int columnIndex=cursor.getColumnIndex(Constant._ID);
        // getInt(int ColumnIndex) 根据指定字段下标获取对应int类型的Value
        int _id=cursor.getInt(columnIndex);

        String name=cursor.getString(cursor.getColumnIndex(Constant.NAME));
        int age=cursor.getInt(cursor.getColumnIndex(Constant.AGE));

        Person person=new Person(_id,name,age);
        list.add(person);
    }
    return list;
}
```

# 执行Sql语句
```java
/**
 * 根据sql语句查询获取cursor对象
 * @param db 数据库对象
 * @param sql 查询语句
 * @param selectionArgs 查询条件的占位符
 * @return 查询结果
 */
public static Cursor selectDataBySql(SQLiteDatabase db,String sql, String[] selectionArgs){
    Cursor cursor=null;
    if (cursor!=null){
        cursor=db.rawQuery(sql,selectionArgs);
    }
    return cursor;
}
```

# SqliteHelper singleton 单例
```java
private static MySqliteHelper helper;
public static MySqliteHelper getInstance(Context context){
    if (helper==null){
        helper = new MySqliteHelper(context);
    }
    return helper;
}
```

# Sql语句查询
```java
db=helper.getWritableDatabase();
// select _id,name,age  from person where _id>10 group by x having x order by x
/*
query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
    String table = 要查询的表
    String[] = 查询表中的字段; null=查询所有
    String selection 表示查询条件 where 语句
    String[] selectionArgs 表示查询占位符的取值
    String group by 表示分组条件 group by子句
    String having 表示筛选条件 having子句
    String orderBy 表示排序条件 order by 子句
 */
cursor=db.query(Constant.TABLE_NAME,null,Constant._ID+"=?",new String[]{"10"},null,null,Constant._ID+" desc");
list=DbManager.cursorToList(cursor);
for (Person p:list){
    Log.i("tag",p.toString());
}
db.close();
```

# 通过Api查询数据
```java
db=helper.getWritableDatabase();
// select _id,name,age  from person where _id>10 group by x having x order by x
/*
query(boolean distinct, String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
    String table = 要查询的表
    String[] = 查询表中的字段; null=查询所有
    String selection 表示查询条件 where 语句
    String[] selectionArgs 表示查询占位符的取值
    String group by 表示分组条件 group by子句
    String having 表示筛选条件 having子句
    String orderBy 表示排序条件 order by 子句
 */
cursor=db.query(Constant.TABLE_NAME,null,Constant._ID+"=?",new String[]{"10"},null,null,Constant._ID+" desc");
list=DbManager.cursorToList(cursor);
for (Person p:list){
    Log.i("tag",p.toString());
}
db.close();
```

# sqlitehelper
```java
public class MySqliteHelper extends SQLiteOpenHelper {

    /**
     * 构造函数
     * @param context 上下文对象
     * @param name 表示创建数据库名称
     * @param factory 游标工厂
     * @param version 表示创建数据库的版本 >=1
     */
    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context){
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }

    /**
     * 当数据库创建时回调的函数
     * @param sqLiteDatabase 表示数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("tag", "-----------onCreate--------");
        String sql = "create table "+Constant.TABLE_NAME +"("
                + Constant._ID+" Integer primary key,"
                + Constant.NAME+" varchar(10), "
                + Constant.AGE+" Integer"
                + ")";
        sqLiteDatabase.execSQL(sql); //执行sql语句
    }

    /**
     * 当数据库版本更新时回调的函数
     * @param sqLiteDatabase 表示数据库对象
     * @param i 表示数据库旧版本
     * @param i1 表示数据库新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("tag", "-----------onUpgrade--------");
    }

    /**
     * 当数据库打开回调的函数
     * @param db 数据库对象
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("tag", "-----------onOpen--------");
    }
}
```

# DbManager
```java
/**
 * Created by Administrator on 2017/11/18.
 * 主要是对数据库操作的工作类
 * 单例模式
 */

public class DbManager {
    private static MySqliteHelper helper;
    public static MySqliteHelper getInstance(Context context){
        if(helper==null){
            helper=new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句在数据库中执行语句
     * @param db 数据库对象
     * @param sql sql语句
     */
    public static void execSQL(SQLiteDatabase db,String sql){
        if(db!=null){
            if (sql!=null && !"".equals(sql)){
                db.execSQL(sql);
            }
        }
    }
}

```

# source
[Android基础教程-SQLite高级操作 @Summer321](http://www.imooc.com/learn/749)
