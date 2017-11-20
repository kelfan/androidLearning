# Android基础教程-SQLite高级操作 sqlite数据库分页 github codes

# 思路
1. 通过adapter显示第一页
1. 通过setOnScrollListener滚动显示
  - 每次触发在列表上增加更多的数据
  - 通过 总条目数/每页显示 获取 页面数
  - 通过 下标和每页显示 获取每次的显示
  - 通过 页面数 知道总共可以翻滚的次数

# sql 语句
```sql
select * from Person limit ?,?; -- 当前页码的第一条数据的下标,总共多少条
```

# 通过adapter绑定数据到页码
```java
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
lv = (ListView)findViewById(R.id.lv);

String path= Environment.getExternalStorageDirectory().getAbsolutePath()
        + File.separator+"info.db";
db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
// 获取数据总条目
totalNum = DbManager.getDataCount(db, Constant.TABLE_NAME);
// 根据总条目与每页展示数据条目 获得总页数
pageNum= (int) Math.ceil(totalNum/(double)pageSize);
if (currentPage == 1) {
    totalList = DbManager.getListByCurrentPage(db, Constant.TABLE_NAME, currentPage, pageSize);
}
adapter=new MyBaseAdapter(this,totalList);
lv.setAdapter(adapter);
```

# 滚动分页
```java
lv.setOnScrollListener(new AbsListView.OnScrollListener(){

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        // AbsListView.OnScrollListener.SCROLL_STATE_IDLE 表示滚动条停止时的状态
        if (isDivPage && AbsListView.OnScrollListener.SCROLL_STATE_IDLE == scrollState) {
            if (currentPage < pageNum) {
                currentPage++;
                // 根据最新的页码加载获取集合存储到数据源中
                totalList.addAll(DbManager.getListByCurrentPage(db,Constant.TABLE_NAME,
                        currentPage,pageSize));
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        isDivPage=((firstVisibleItem+visibleItemCount)==totalItemCount);
    }
});
```

# adapter 匹配数据到页面
```java
@Override
public View getView(int i, View convertView, ViewGroup parent) {
    ViewHolder holder=null;
    if (convertView==null){
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        holder=new ViewHolder();
        holder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
        holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        holder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
        convertView.setTag(holder);
    }else{
        holder=(ViewHolder) convertView.getTag();
    }
    holder.tv_id.setText(list.get(i).get_id()+"");
    holder.tv_name.setText(list.get(i).getName()+"");
    holder.tv_age.setText(list.get(i).getAge()+"");
    return convertView;
}

static class ViewHolder{
    TextView tv_id,tv_name,tv_age;
}
```

# 得到数据总条目
```java
/**
 * 根据数据库以及数据表名称获取表中数据总条目
 * @param db 数据库对象
 * @param tablename 数据表名称
 * @return 数据总条目
 */
public static int getDataCount(SQLiteDatabase db, String tablename) {
    int count=0;
    if (db != null) {
        Cursor cursor=db.rawQuery("select * from "+tablename,null);
        count=cursor.getCount(); //获取cursor中的数据总数
    }
    return count;
}
```

# 根据页码得到相应的数据集合
```java
/**
 * 根据当前页码查询获取该页码对应的集合数据
 * @param db 数据库对象
 * @param tablename 数据表名称
 * @param CurrentPage 当前页码
 * @param pageSize 每页展示数据条目
 * @return 当前页对应D的集合

 */
public static List<Person> getListByCurrentPage(SQLiteDatabase db,String tablename,int CurrentPage, int pageSize){
    int index=(CurrentPage-1)*pageSize; // 获取当前页码第一条数据的下标
    Cursor cursor=null;
    if (db!=null){
        String sql = "select * from " + tablename + " limit ?,?";
        cursor = db.rawQuery(sql,new String[]{index+"",pageSize+""});
    }
    return cursorToList(cursor);
}
```

# source
[Android基础教程-SQLite高级操作 @Summer321](http://www.imooc.com/learn/749)
