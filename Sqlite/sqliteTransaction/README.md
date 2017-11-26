# Android基础教程-SQLite高级操作 sqlite数据库事务操作 github codes
# sqlite 事务的使用 = 批量处理的时候使用增加效率和一致性
beginTransaction() 开启事务
setTransactionSuccessful() 设置提交事务
endTransaction() 关闭事务

```java
public void insertData(View view){
    SQLiteDatabase db = helper.getWritableDatabase();
    // 1. 数据库显式开启事务
    db.beginTransaction();
    for (int i=1;i<=100;i++){
        String sql="insert into "+Constant.TABLE_NAME+" values("+i+",'小慕"+i+"',18)";
        db.execSQL(sql);
    }
    // 2. 提交当前事务
    db.setTransactionSuccessful();
    // 3. 关闭事务
    db.endTransaction();
    db.close();
}
```

# source
[Android基础教程-SQLite高级操作 @Summer321](http://www.imooc.com/learn/749)
